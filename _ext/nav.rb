
module Awestruct
  module Extensions
    class Nav

      def initialize()
      end

      def execute(site)
        if site.nav
          nav = {}
          site.nav.each do |k, v|
            nav[k.to_s] = build(v, '', k, site)
          end
          site.nav = nav
        end
      end

      def build(object, url, parent, site)
        if parent != nil
          url = "#{url}/#{parent}"
        end
        r = {} 
        if Hash === object
          r['children'] = {}
          object.each do |key, value|
            if key.to_s == 'label' || key.to_s == 'url' || key.to_s == 'description'
              r[key] = value
            else
              r['children'][key] = build(value, url, key, site)
            end
          end
        end
        s = OpenStruct.new(r)
        s.url ||= url
        if s.url !~ /^http:\/\//
          s.url = site.base_url + s.url
        end 
        s.label ||= parent.to_s.split('-').each{|word| word.capitalize!}.join(' ')
        s
      end

    end
  end
end
