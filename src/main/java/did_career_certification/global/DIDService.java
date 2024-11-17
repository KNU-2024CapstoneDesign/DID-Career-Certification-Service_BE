package did_career_certification.global;

import static did_career_certification.config.Web3jConfig.CONTRACT_ADDRESS;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
public class DIDService {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Credentials credentials;

    /**
     * 스마트 계약 로드 메서드
     *
     * @return 로드된 DIDRegistry 스마트 계약 인스턴스
     */
    private DIDRegistry loadContract() {
        return DIDRegistry.load(CONTRACT_ADDRESS, web3j, credentials, new DefaultGasProvider());
    }

    /**
     * 스마트 계약에 certificate 저장
     *
     * @param certificate 저장할 certificate 내용
     * @return 저장된 certificate의 ID
     * @throws Exception 예외 처리
     */
    public BigInteger storeCertificate(String certificate) throws Exception {
        try {
            // 계약 로드
            DIDRegistry contract = loadContract();
            // certificate 저장하고, 반환된 ID
            TransactionReceipt receipt = contract.setCertificate(certificate).send();
            // Solidity에서 반환된 인증서 ID
            BigInteger certificateId = contract.getCertificateCount().send();

            System.out.println("Certificate stored with ID: " + certificateId);
            return certificateId.subtract(BigInteger.ONE);
        } catch (Exception e) {
            throw new Exception("Failed to store certificate: " + e.getMessage(), e);
        }
    }

    /**
     * 특정 ID의 certificate 조회
     *
     * @param issuerAccount 조회할 Issuer의 계좌 정보
     * @param certificateId 조회할 certificate ID
     * @return 조회된 certificate 내용
     * @throws Exception 예외 처리
     */
    public String getCertificate(String issuerAccount, BigInteger certificateId) throws Exception {
        try {
            // 계약 로드
            DIDRegistry contract = loadContract();
            // 인증서 내용 조회
            String certificate = contract.getCertificate(issuerAccount, certificateId).send();

            System.out.println("Retrieved certificate: " + certificate);
            return certificate;
        } catch (Exception e) {
            throw new Exception("Failed to retrieve certificate: " + e.getMessage(), e);
        }
    }
}
