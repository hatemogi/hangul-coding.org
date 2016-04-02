-- 프로젝트 테이블
CREATE TABLE 프로젝트 (
  소유자   VARCHAR(32)  NOT NULL REFERENCES 이용자(아이디) ON DELETE CASCADE,
  이름     VARCHAR(64)  NOT NULL,
  설명     VARCHAR(256) NOT NULL,
  공개     BOOLEAN      NOT NULL DEFAULT TRUE,
  생성일시 TIMESTAMP    NOT NULL DEFAULT now(),
  갱신일시 TIMESTAMP    NOT NULL DEFAULT now(),
  PRIMARY KEY (소유자, 이름)
);

CREATE INDEX 프로젝트_최신순_인덱스 ON 프로젝트 (소유자, 이름, 갱신일시 DESC);
