require 'authors_helper'
require 'uri'

module Awestruct::Extensions::TweetHelper
  def tweet_page_url(text,nick = nil,link = nil)
    handle = nick ? lookup_twitter_handle(nick) : nil
    # The extra 6 is for " via @"
    handleLen = handle ? handle.length + 6 : 0
    msg = text
    msgLen = msg.length
    # A URL takes 23 characters, we want two spaces so we have a max of 115 for the text len
    allowedLen = (115 - handleLen)
    if msgLen > allowedLen
      allowedLen = allowedLen - 3
      msg = msg[0...allowedLen] + "..."
    end
    msg = "https://twitter.com/intent/tweet/?text=" + URI.encode_www_form([msg])
    abs_link = link
    if link
      abs_link = link.start_with?("http") ? link : "https:" + link
    end
    msg = abs_link ? msg + "&url=" + URI.encode_www_form([abs_link]) : msg
    handle ? msg + "&via=" + handle : msg
  end
end
