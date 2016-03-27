(function (document, window, $) {
    $(function() {
        console.log("한글코딩!");
    });

    $(function() {
        marked.setOptions({
            highlight: function(code) {
                return hljs.highlightAuto(code).value;
            }
        });
        $('div[data-markdown]').each(function() {
            var div = $(this);
            var path = "/md/" + div.attr("data-markdown");
            $.get(path, function(body, xhr) {
                console.log(body, xhr);
                div.html(marked(body));
            });
        });
    });

    $(function() {
        $('pre code').each(function(i, block) {
            hljs.highlightBlock(block);
        });
    });
})(document, window, jQuery);
