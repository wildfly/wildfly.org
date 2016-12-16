module Awestruct::Extensions::AuthorsHelper
  def lookup_name(nick)
    author = nick ? site.authors[nick] : nil
    name = author ? author["name"] : nick
    name ? name : "Unknown"
  end
end
