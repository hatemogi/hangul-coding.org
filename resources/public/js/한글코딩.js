(function (document, window, $) {
    $(function 마크다운() {
        marked.setOptions({
            highlight: function(코드) {
                return hljs.highlightAuto(코드).value;
            }
        });
        $('div[data-markdown]').each(function() {
            var div = $(this);
            var 파일 = "/md/" + div.attr("data-markdown");
            $.get(파일, function(본문, xhr) {
                div.html(marked(본문));
            });
        });
    });

    $(function 하이라이트() {
        $('pre code').each(function(i, 블럭) {
            hljs.highlightBlock(블럭);
        });
    });

    $(function 코드미러() {
        var src = "-- 프로젝트 테이블\nCREATE TABLE 프로젝트 (\n"
                + "  소유자   VARCHAR(32) NOT NULL REFERENCES 이용자(아이디) ON DELETE CASCADE,\n"
                + "  이름     VARCHAR(64) NOT NULL,\n"
                + "  설명     VARCHAR(255) NOT NULL,\n"
                + "  공개     BOOLEAN NOT NULL DEFAULT TRUE,\n"
                + "  생성일시 TIMESTAMP NOT NULL DEFAULT NOW(),\n"
                + "  갱신일시 TIMESTAMP NOT NULL DEFAULT NOW(),\n"
                + "  PRIMARY KEY (소유자, 이름)\n"
                + ");";
        CodeMirror(document.getElementById('codemirror'),
                   {lineNumbers: true, mode: "sql", theme: "default",
                    value: src});

    });

})(document, window, jQuery);
