
module Awestruct
  module Extensions
    module RemotePartial

      def remotePartial(url)
        url_tmp = url.sub('http://', '')
        r = 'remote_partial/' + url_tmp[/(.*)\/[^\/].+$/, 1]
        tmp = File.join(tmp(site.tmp_dir, r), File.basename(url_tmp))
        getOrCache(tmp, url)
      end

    end
  end
end
