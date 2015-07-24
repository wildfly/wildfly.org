require 'net/http'
require 'uri'

class Release

  def initialize()
  end

  def execute(site)
    (site.releases).each do |release|
      version = release[:version]
      {:zip => '.zip',  :tgz => '.tar.gz', :srczip => '-src.zip', :srctgz => '-src.tar.gz'}.each do |kind, suffix|
        uri = URI.parse("http://download.jboss.org/wildfly/#{version}/wildfly-#{version}#{suffix}")
        release[kind] = {:url => uri, :size => compute_size(uri)}
      end
      {:zip => '.zip',  :tgz => '.tar.gz', :srczip => '-src.zip', :srctgz => '-src.tar.gz'}.each do |kind, suffix|
        uri = URI.parse("http://download.jboss.org/wildfly/#{version}/servlet/wildfly-web-#{version}#{suffix}")
        size = compute_size(uri)
        if (size != "unknown")
          release[:servlet] = {} unless release.has_key?(:servlet)
          release[:servlet][kind] = {:url => uri, :size => size}
        end
        uri = URI.parse("http://download.jboss.org/wildfly/#{version}/servlet/wildfly-servlet-#{version}#{suffix}")
        size = compute_size(uri)
        if (size != "unknown")
          release[:servlet] = {} unless release.has_key?(:servlet)
          release[:servlet][kind] = {:url => uri, :size => size}
        end
      end
      if release.has_key?("updateforversion")
        uri = URI.parse("http://download.jboss.org/wildfly/#{version}/wildfly-#{version}-update.zip")
        release[:update] = {:url => uri, :size => compute_size(uri)}
      end
      if release.has_key?("quickversion")
        version = release[:quickversion] 
        uri = URI.parse("http://download.jboss.org/wildfly/#{version}/quickstart-#{version}.zip")
        release[:quickstart] = {:url => uri, :size => compute_size(uri)}
      end
    end
  end

  def compute_size(uri)
    Net::HTTP.start( uri.host, uri.port ) do |http|
      response = http.head( uri.path )
      b = response['content-length'] || ''
      if ( response.code == "200" and ! b.empty? )
         formatBytes(b)
      else
        'unknown'
      end
    end
  end

  def formatBytes(bytes)
    bytes = bytes.to_f
    units = ["bytes", "KB", "MB", "GB", "TB", "PB"]

    i = 0
    while bytes > 1024 and i < 6 do
      bytes /= 1024
      i += 1
    end

    sprintf("%.0f %s", bytes, units[i])
  end  
end

