module Awestruct::Extensions::AuthorsHelper
  def lookup_name(nick)
    author = nick ? site.authors[nick] : nil
    name = author ? author["name"] : nick
    name ? name : "Unknown"
  end
  def lookup_twitter_handle(nick)
    author = nick ? site.authors[nick] : nil
    name = author ? author["twitter"] : nick
    name ? name : "WildFlyAS"
  end
end
