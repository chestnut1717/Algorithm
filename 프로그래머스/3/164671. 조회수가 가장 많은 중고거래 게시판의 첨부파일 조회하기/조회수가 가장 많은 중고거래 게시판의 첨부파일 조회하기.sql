-- 첨부파일 경로는 FILE ID를 기준으로 내림차순 정렬해주세요.
-- 기본적인 파일경로는 /home/grep/src/
-- 게시글 ID를 기준으로 디렉토리가 구분
-- 파일이름은 파일 ID, 파일 이름, 파일 확장자로 구성되도록 출력
-- 조회수가 가장 높은 게시물은 하나만 존재합니다
-- CONCAT('/home/grep/src/', F.FILE_NAME)
SELECT CONCAT('/home/grep/src/',BOARD_ID, '/', FILE_ID, FILE_NAME, FILE_EXT) AS FILE_PATH
FROM USED_GOODS_FILE
WHERE BOARD_ID = 
(SELECT DISTINCT B.BOARD_ID
 FROM USED_GOODS_BOARD AS B
INNER JOIN USED_GOODS_FILE AS F
WHERE B.BOARD_ID = F.BOARD_ID
ORDER BY B.VIEWS DESC
 LIMIT 1)
 ORDER BY FILE_ID DESC;