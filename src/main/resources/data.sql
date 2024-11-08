INSERT INTO STUDENT(id, name, college, major, degree, academic_status)
VALUES (201911362, 'KSH', 'IT', 'CSE', 'BACHELOR', 'ENROLLED');

INSERT INTO UNIV(name, request_Api, did)
VALUES ('강원대학교(춘천)', 'http://localhost:8080/api/issuer/vc', 'did:ether:0x2b7BB9a7b7e32fbCE880B91Ad8e0979856a24083');

INSERT INTO HOLDER_VERIFIER_TB(name, request_Api, did)
VALUES ('VERIFIER 1', 'http://localhost:8080/api/verifier/vp', 'did:ether:0x878B014D9c7987a3BA18732C210CEBabeF19Df74')