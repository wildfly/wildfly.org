require File.join File.dirname(__FILE__), 'tweakruby'
require_relative 'common'

Awestruct::Extensions::Pipeline.new do
  # extension Awestruct::Extensions::Posts.new( '/news' ) 
  extension Awestruct::Extensions::Indexifier.new
end

