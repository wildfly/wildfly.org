require File.join File.dirname(__FILE__), 'tweakruby'
require_relative 'common'
require 'awestruct/extensions/remotePartial'
require_relative 'remotePartial'




Awestruct::Extensions::Pipeline.new do

  extension Awestruct::Extensions::Indexifier.new

  transformer Awestruct::Extensions::Minify.new

  helper Awestruct::Extensions::RemotePartial
  helper Awestruct::Extensions::Partial
  helper Awestruct::Extensions::GoogleAnalytics

  # Generate a sitemap.xml
  extension Awestruct::Extensions::Sitemap.new

end

