(function (document, window, $) {
    $(document).ready(function() {
        console.log("한글코딩!");
    });

    $(document).ready(function() {
        $('pre code').each(function(i, block) {
            hljs.highlightBlock(block);
        });
    });
})(document, window, jQuery);
