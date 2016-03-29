(function (document, window, $) {

    var 소스코드_에디터 = function() {
        $('pre code.소스코드').each(function(i, 코드) {
            var pre = 코드.parentNode;
            var 소스 = $(코드).text();
            var 언어 = $(코드).attr("data-lang");
            var 테마 = "default";
            var 옵션 = {value: 소스, lineNumbers: false,
                        mode: 언어, theme: 테마};
            CodeMirror(function(e) {
                pre.parentNode.replaceChild(e, pre);
            }, 옵션);
        });
    };

    $(function 마크다운() {
        var 변환 = new marked.Renderer();

        // 기본 heading처리는 h태그 텍스트가 영문(+숫자)만 잘 처리 되기에,
        // 아래 코드로 별도 처리
        변환.heading = function(텍스트, 레벨) {
            var 링크 = 텍스트.toLowerCase().replace(/[\s]+/g, '-');
            return '<h' + 레벨 + '><a name="' + 링크 +
                '" class="anchor" href="#' + 링크 +
                '"><span class="header-link"></span></a>' +
                텍스트 + '</h' + 레벨 + '>';
        };

        변환.code = function(코드, 언어, escape) {
            return '<pre><code class="소스코드" data-lang="' + 언어 +
                '">' + 코드 + '</code></pre>';
        };

        marked.setOptions({
            /* highlight: function(코드) {
                return hljs.highlightAuto(코드).value;
            },*/
            sanitize: true,
            renderer: 변환
        });
        $('div[data-markdown]').each(function() {
            var div = $(this);
            var 파일 = "/md/" + div.attr("data-markdown");
            $.get(파일, function(본문, xhr) {
                div.html(marked(본문));
                소스코드_에디터();
            });
        });
    });

    $(function 소스코드() {
        $('pre.소스 code').each(function() {
            var code = $(this);
            var 파일 = "/src/" + code.attr("data-src");
            $.get(파일, function(본문, xhr) {
                code.html(hljs.highlightAuto(본문).value);
            });
        });
    });

    $(function 하이라이트() {
        return "건너뛰기";
        $('pre code').each(function(i, 블럭) {
            hljs.highlightBlock(블럭);
        });
    });

})(document, window, jQuery);
