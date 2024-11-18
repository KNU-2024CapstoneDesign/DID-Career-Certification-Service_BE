INSERT INTO STUDENT(id, name, college, major, degree, academic_status)
VALUES (201911362, 'KSH', 'IT', 'CSE', 'BACHELOR', 'ENROLLED');

INSERT INTO ISSUER(name, request_Api, did, require_data)
VALUES ('강원대학교(춘천)', 'http://localhost:8080/api/issuer/vc', 'did:ether:0x2b7BB9a7b7e32fbCE880B91Ad8e0979856a24083', 'studentId');

INSERT INTO HOLDER_VERIFIER_TB(name, request_Api, did)
VALUES ('VERIFIER 1', 'http://localhost:8080/api/verifier/vp', 'did:ether:0x878B014D9c7987a3BA18732C210CEBabeF19Df74');

INSERT INTO HOLDER(wallet_address, password, name, profile_image_url)
VALUES ('1', '123', '간호두리', 'https://github.com/KNU-2024CapstoneDesign/UoT_BE/blob/Develop/src/main/resources/static/images/%EA%B0%84%ED%98%B8%EB%8C%80%20%EA%B3%B0%EB%91%90%EB%A6%AC.jpg?raw=true'),
       ('2', '123', '선생두리', 'https://github.com/KNU-2024CapstoneDesign/UoT_BE/blob/Develop/src/main/resources/static/images/%EC%82%AC%EB%B2%94%EB%8C%80%20%EA%B3%B0%EB%91%90%EB%A6%AC.png?raw=true'),
       ('3', '123', '의사두리', 'https://github.com/KNU-2024CapstoneDesign/UoT_BE/blob/Develop/src/main/resources/static/images/%EC%9D%98%EB%8C%80%20%EA%B3%B0%EB%91%90%EB%A6%AC.jpg?raw=true'),
       ('4', '123', '컴공두리', 'https://github.com/KNU-2024CapstoneDesign/UoT_BE/blob/Develop/src/main/resources/static/images/%EC%BB%B4%EA%B3%B5%20%EA%B3%B0%EB%91%90%EB%A6%AC.png?raw=true');

INSERT INTO APPLICANT(id, holder_account, holder_name, profile_image_url, submit_time)
VALUES (1, '1', '간호두리', 'https://github.com/KNU-2024CapstoneDesign/UoT_BE/blob/Develop/src/main/resources/static/images/%EA%B0%84%ED%98%B8%EB%8C%80%20%EA%B3%B0%EB%91%90%EB%A6%AC.jpg?raw=true', '2024-11-19 09:00:00'),
       (2, '2', '선생두리', 'https://github.com/KNU-2024CapstoneDesign/UoT_BE/blob/Develop/src/main/resources/static/images/%EC%82%AC%EB%B2%94%EB%8C%80%20%EA%B3%B0%EB%91%90%EB%A6%AC.png?raw=true', '2024-11-19 09:10:00'),
       (3, '3', '의사두리', 'https://github.com/KNU-2024CapstoneDesign/UoT_BE/blob/Develop/src/main/resources/static/images/%EC%9D%98%EB%8C%80%20%EA%B3%B0%EB%91%90%EB%A6%AC.jpg?raw=true', '2024-11-19 10:13:00'),
       (4, '4', '컴공두리', 'https://github.com/KNU-2024CapstoneDesign/UoT_BE/blob/Develop/src/main/resources/static/images/%EC%BB%B4%EA%B3%B5%20%EA%B3%B0%EB%91%90%EB%A6%AC.png?raw=true', '2024-11-19 10:43:00');
