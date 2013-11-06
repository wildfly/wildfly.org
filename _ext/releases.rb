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
      version = release[:quickversion] 
      uri = URI.parse("http://download.jboss.org/wildfly/#{version}/quickstart-#{version}.zip")
      release[:quickstart] = {:url => uri, :size => compute_size(uri)}
    end
  end

  def compute_size(uri)
    Net::HTTP.start( uri.host, uri.port ) do |http|
      response = http.head( uri.path )
      b = response['content-length'] || ''
      if ( ! b.empty? )
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

