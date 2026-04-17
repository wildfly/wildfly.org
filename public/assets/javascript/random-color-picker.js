$(document).ready(function(){
    // Use consistent teal color scheme for all news items
    var consistentColor = "teal";

    // Consistent color class for news feed on /news
    $(".news-list-item").each(function(){
        $(this).addClass(consistentColor);
    });

    // Consistent color class for news feed in blocks format on homepage
    $(".news-block-item").each(function(){
        $(this).addClass(consistentColor);
    });
});
