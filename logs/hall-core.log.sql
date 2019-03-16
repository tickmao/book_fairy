2019-03-17 00:34:10,538 [http-nio-8083-exec-21] ==>  Preparing: SELECT id AS id,href,chapter,bookname_id AS booknameId FROM SECTION_CHAPTERS WHERE (bookname_id = ?) 
2019-03-17 00:34:10,621 [http-nio-8083-exec-21] ==> Parameters: 1(String)
2019-03-17 00:34:10,815 [http-nio-8083-exec-21] <==      Total: 65
2019-03-17 00:34:20,250 [http-nio-8083-exec-24] ==>  Preparing: SELECT id AS id,chapter,content,prev,`next`,section_chapter_id AS sectionChapterId FROM SECTION_DETAILS WHERE (section_chapter_id = ?) 
2019-03-17 00:34:20,252 [http-nio-8083-exec-24] ==> Parameters: 2(String)
2019-03-17 00:34:20,439 [http-nio-8083-exec-24] <==      Total: 1
