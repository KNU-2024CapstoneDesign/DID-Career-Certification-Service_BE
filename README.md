<h1 style="text-align:center;">UoT</h1>

<!-- 목차 -->
<div style="border: 2px solid #ddd; border-radius: 10px; padding: 20px; margin: 20px 0;">
  <h2 style="font-size: 1.8rem; text-align: center;">목차</h2>
  <ul style="list-style: none; text-align: center; padding: 0; font-size: 1.2rem;">
    <li><a href="#기획-배경">🚀 기획 배경</a></li>
    <li><a href="#서비스-개요">📋 서비스 개요</a></li>
    <li><a href="#주요-기능">✨ 주요 기능</a></li>
    <li><a href="#시연-영상">🎥 시연 영상</a></li>
    <li><a href="#멤버-소개">👩‍💻 멤버 소개</a></li>
    <li><a href="#기술스택">🛠️ 기술스택</a></li>
    <li><a href="#프로젝트-아키텍쳐">🏗️ 프로젝트 아키텍쳐</a></li>
    <li><a href="#erd">🗂️ ERD</a></li>
    <li><a href="#테스트 시나리오, 결과">📄 API 명세서</a></li>
    <li><a href="#배포">🌐 배포</a></li>
  </ul>
</div>

<!-- 기획 배경 -->
<h2>🚀 기획 배경</h2>
<div id="기획-배경" style="border: 2px solid #ddd; border-radius: 10px; padding: 20px; margin: 20px 0;">
  <p>현재 교내 증명서 발급 시스템은 주로 두 가지 방법으로 증명서를 제공하고 있다. 첫 번째 방법은 증명서를 다운로드하여 제출처에 필요한 서류를 직접 관리하고 제출하는 방식으로, 본인이 제출처에 필요한 모든 서류를 각각 다운받아 관리 및 제출해야 하는 번거로움이 있다. 두 번째 방법은 제출 기관에 증명서를 유료로 전송하는 방식으로, 보다 편리하지만 비용이 발생하고, 중앙집중식 서버에 의존하기 때문에 보안상 문제가 발생할 경우 시스템을 신뢰하기 어렵다는 보안 취약성이 존재한다.</p>
</div>

<!-- 서비스 개요 -->
<h2>📋 서비스 개요</h2>
<div id="서비스-개요" style="border: 2px solid #ddd; border-radius: 10px; padding: 20px; margin: 20px 0;">
  <p>이 프로젝트의 목적은 기존 증명서 발급 시스템의 보안 및 효율성 문제를 해결하기 위해 DID(Decentralized Identity, 분산 신원 확인: 정보의 주체가 자신의 신원을 중앙기관 없이 자율적으로 관리할 수 있도록 하는 분산형 신원 인증 방식) 기술을 활용한다. 따라서 중앙집중형 신원 인증 방식과 달리, 개인이 자신에 관한 데이터를 통제할 수 있으며, 데이터를 암호화하여 블록체인과 같은 분산 원장 기술에 저장함으로써 보안을 강화한다. 이를 통해 증명서 발급 및 관리 과정에서 발생할 수 있는 보안 취약성을 개선할 수 있다.</p>
</div>

<!-- 주요 기능 -->
<h2>✨ 주요 기능</h2>
<div id="주요-기능" style="border: 2px solid #ddd; border-radius: 10px; padding: 20px; margin: 20px 0;">
  <h4>✅ 증명서 발급</h4>
  <ol>
    <li>Holder는 Issuer에게 증명서 발급에 필요한 정보를 전달하여 증명서 발급을 요청한다.</li>
    <li>Issuer는 Holder가 제출한 정보를 바탕으로 증명서를 발급하여 블록체인에 저장한다.</li>
    <li>Issuer는 발급한 증명서 대신 블록체인에 저장된 증명서를 찾아볼 수 있는 정보를 담은 <ins>VC</ins>를 Holder에게 반환한다.</li>
  </ol>
  <h4>✅ 증명서 제출</h4>
  <ol>
    <li>Verifier에게 제출할 증명서들의 VC를 선택한다.</li>
    <li>해당 VC집합과 증명서의 신원을 증명할 수 있는 본인의 정보를 포함하여 VP를 Verifier에게 제출한다.</li>
  </ol>
  <h4>✅ 증명서 검증</h4>
  <ol>
    <li>제출된 VP의 정보를 바탕으로 증명서와 해당 증명서의 신원을 검증한다.</li>
  </ol>
</div>

<!-- 시연 영상 -->
<h2>🎥 시연 영상</h2>
<div id="시연-영상" style="border: 2px solid #ddd; border-radius: 10px; padding: 20px; margin: 20px 0;">
  <video controls width=100%>
    <source src="https://github.com/KNU-2024CapstoneDesign/UoT_BE/blob/Develop/src/main/resources/static/video/UoT_%EC%8B%9C%EC%97%B0%EC%98%81%EC%83%81.mp4" type="video/mp4" />
    Your browser does not support the video tag.
  </video>
</div>

<!-- 멤버 소개 -->
<h2>👩‍💻 멤버 소개</h2>
<div id="멤버-소개" style="border: 3px solid #ddd; border-radius: 20px; padding: 30px; margin: 30px 0;">
  <table style="width: 100%; border-collapse: collapse; margin-top: 20px;">
    <tr>
      <th style="text-align: center; vertical-align: middle;">고승현</th>
      <th style="text-align: center; vertical-align: middle;">김동언</th>
      <th style="text-align: center; vertical-align: middle;">정동한</th>
    </tr>
    <tr>
      <td style="text-align: center; vertical-align: middle;"><img src="https://avatars.githubusercontent.com/u/62418972?v=4" alt="고승현" /></td>
      <td style="text-align: center; vertical-align: middle;"><img src="https://avatars.githubusercontent.com/u/85996392?v=4" alt="김동언" /></td>
      <td style="text-align: center; vertical-align: middle;"><img src="https://avatars.githubusercontent.com/u/98398484?v=4" alt="정동한" /></td>
    </tr>
    <tr>
      <td style="text-align: center; vertical-align: middle;">FullStack</td>
      <td style="text-align: center; vertical-align: middle;">FrontEnd</td>
      <td style="text-align: center; vertical-align: middle;">BlockChain</td>
    </tr>
    <tr>
      <td style="text-align: center; vertical-align: middle;">Tech leader</td>
      <td style="text-align: center; vertical-align: middle;">Designer</td>
      <td style="text-align: center; vertical-align: middle;">Team leader</td>
    </tr>
    <tr>
      <td style="text-align: center; vertical-align: middle;">
        <a href="https://github.com/seorinnn">Github</a>
      </td>
      <td style="text-align: center; vertical-align: middle;">
        <a href="https://github.com/seorinnn">Github</a>
      </td>
      <td style="text-align: center; vertical-align: middle;">
        <a href="https://github.com/seorinnn">Github</a>
      </td>
    </tr>
  </table>
</div>

<!-- 기술스택 -->
<h2>🛠️ 기술스택</h2>
<div id="기술스택" style="border: 2px solid #ddd; border-radius: 10px; padding: 20px; margin: 20px 0;">
  <h3> Backend</h3>
    <img src="https://img.shields.io/badge/Java-23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
    <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"/>
    <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"/>
    <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"/>
    <img src="https://img.shields.io/badge/Spring Data JPA-5DB33F?style=for-the-badge&logo=Spring Data JPA&logoColor=white"/>
    <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white"/>
    <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white"/>
    <img src="https://img.shields.io/badge/Web3j-F16822?style=for-the-badge&logo=web3j&logoColor=white"/>
    <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white"/>
  <h3> Frontend</h3>
    <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=white">
    <img src="https://img.shields.io/badge/typescript-3178C6?style=for-the-badge&logo=typescript&logoColor=white">
    <img src="https://img.shields.io/badge/vite-646CFF?style=for-the-badge&logo=vite&logoColor=white">
    <img src="https://img.shields.io/badge/chakra--ui-319795?style=for-the-badge&logo=chakraui&logoColor=white">
    <img src="https://img.shields.io/badge/emotion-DB7093?style=for-the-badge&logo=emotion&logoColor=white">
    <img src="https://img.shields.io/badge/react--router-CA4245?style=for-the-badge&logo=reactrouter&logoColor=white">
    <img src="https://img.shields.io/badge/eslint-4B32C3?style=for-the-badge&logo=eslint&logoColor=white">
    <img src="https://img.shields.io/badge/prettier-F7B93E?style=for-the-badge&logo=prettier&logoColor=white">
  <h3> Database</h3>
    <img src="https://img.shields.io/badge/H2-1B6AC6?style=for-the-badge&logo=H2&logoColor=white"/>
  <h3> BlockChain</h3>
    <img src="https://img.shields.io/badge/Ethereum-3C3C3D?style=for-the-badge&logo=Ethereum&logoColor=white"/>
    <img src="https://img.shields.io/badge/Solidity-363636?style=for-the-badge&logo=solidity&logoColor=white"/>
  <h3> Application</h3>
    <img src="https://img.shields.io/badge/IntelliJ-000000?style=for-the-badge&logo=intellijidea&logoColor=white"/>
    <img src="https://img.shields.io/badge/VisualStudio-0019F4?style=for-the-badge&logo=visualstudiocode&logoColor=white"/>
    <img src="https://img.shields.io/badge/REMIXIDLE-0076A3?style=for-the-badge&logo=METAMASK&logoColor=white"/>
    <img src="https://img.shields.io/badge/GANACHE-6F4F28?style=for-the-badge&logo=METAMASK&logoColor=white"/>
    <img src="https://img.shields.io/badge/METAMASK-FF7F00?style=for-the-badge&logo=METAMASK&logoColor=white"/>
  </div>

<!-- 프로젝트 아키텍쳐 -->
<h2>🏗️ 프로젝트 아키텍쳐</h2>
<div id="프로젝트-아키텍쳐" style="border: 2px solid #ddd; border-radius: 10px; padding: 20px; margin: 20px 0;">
  <p>아래는 프로젝트 아키텍쳐 다이어그램입니다:</p>
  <img src="https://via.placeholder.com/800x400" alt="프로젝트 아키텍쳐" />
</div>

<!-- ERD -->
<h2>🗂️ ERD</h2>
<div id="erd" style="border: 2px solid #ddd; border-radius: 10px; padding: 20px; margin: 20px 0;">
  <p>아래는 프로젝트의 ERD(Entity Relationship Diagram)입니다:</p>
  <img src="" alt="ERD" />
</div>

<!-- API 명세서 -->
<h2>📄 API 명세서</h2>
<div id="api-명세서" style="border: 2px solid #ddd; border-radius: 10px; padding: 20px; margin: 20px 0;">
  <p>API 명세서는 <ins>Swagger</ins> 지원 예정입니다.</p>
</div>

<!-- 배포 -->
<h2>🌐 배포</h2>
<div id="배포" style="border: 2px solid #ddd; border-radius: 10px; padding: 20px; margin: 20px 0;">
  <p>서비스는 <ins>로컬 환경</ins>에서 테스트해볼 수 있습니다.</p>
</div>
