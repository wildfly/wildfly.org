require 'bootstrap-sass'
require File.join File.dirname(__FILE__), 'tweakruby'
require_relative 'common'
require 'awestruct/extensions/remotePartial'
require_relative 'remotePartial'
require 'awestruct/extensions/minify'
require_relative 'releases'
Awestruct::Extensions::Pipeline.new do

  extension Awestruct::Extensions::Indexifier.new
  extension Release.new
  helper Awestruct::Extensions::RemotePartial
  helper Awestruct::Extensions::Partial
  helper Awestruct::Extensions::GoogleAnalytics

  # Generate a sitemap.xml
  extension Awestruct::Extensions::Sitemap.new
  transformer Awestruct::Extensions::Minify.new

end

