# SmartWarehouse_빅데이터 활용, IoT시스템을 갖춘 스마트창고

### (Feat. SpringFramework, Android, Hadoop, R)

<br>

### [🎬YouTube에서 서비스 시연연상 보기](https://youtu.be/eaqHH8ln0Bo)

### [📝Notion에서 프로젝트 보고서 읽기](https://www.notion.so/SmartWarehouse_-IoT-14d0bd97353d49468dc1bfa4feafc94a)

<br><br>

### 목차

1. **프로젝트 개요**<br>
1-1. 배경<br>
1-2. 내용<br>
1-3. 기대효과<br>
1-4. 기간 및 절차<br>
1-5. 인원 및 역할<br>

2. **사용자WorkFlow 및 기능소개(동영상)**<br>

3. **시스템 구성도 및 사용 기술**<br>
3-1. 구성도<br>
3-2. 기술스택<br>
3-3. ERD<br>

4. **시스템 컴포넌트별 상세**<br>
    4-1. ERP (Web Application Server) **Hadoop/R*<br>
    4-2. TCP/IP Server<br>
    4-3. 창고 DASHBOARD (Android Tablet)<br>
    4-4. 지게차 (TCP/IP · CAN Protocol)<br>

  <br><br><br>

---

### 1. 프로젝트 개요

**1-1. 배경**

- 기존 창고(지게차+파렛트랙)에 대한 전산화 및 자동화 도입

- 앞으로 사업 확장에 유연한 시스템 구축

  <br>

**1-2. 내용**

① ***ERP개발***을 통한 물류운영 전산화

② 스마트 창고 시스템 도입을 통한 ***물류자동화***

<br>

**1-3. 기대효과**

- 창고 무인 자동화를 통한 ***24시간 운영***<br>
  → 물류센터의 유효 가동시간을 증가<br>
  → ***물류 처리량 증가*** 및 ***인건비 감소***

- 창고 운영 기록 통계를 바탕으로 한 ***효율적 재고배치*** 솔루션 제공<br>
  → 지게차 동선 최적화 및 운영 ***비용 감소***<br>

  <br>

**1-4. 프로젝트 기간 및 관리**

- 기간 : 20.3.23(월) ~ 20.4.29(수) ***약 5주***

- 관리 : GitHub(소스코드), 문서(GoogleDocs), 커뮤니케이션(Slack)

  <br>

1-5. 인원 및 역할 (5인)

- **강동욱**(팀장) <br>
  : `프로젝트 기획`, `창고DashBoard(Android Tablet) 제작`, `최종 보고 발표`

- **김연주** <br>
  : `Hadoop 서버 구축` , `R을 통한 데이터 분석으로 재고배치효율화 솔루션 도출`

- **백대현** <br>
  : ERP(Web Application Server) `지게차 관리기능 구현`, `DB 구축`

- **이슬** <br>
  : ERP(Web Application Server) `재고 등록·입출고 기능` / `로그인 및 직원 관리기능` / `솔루션 화면` 구현, `DB 구축`

- **최여진** <br>
  : `지게차(LattePanda 및 CanPro활용한 프로젝트 환경) 구축`, `TCP/IP서버 구축`, `각 시스템 컴포넌트 네트워크 연동`, `AWS서버 구축`, `서비스 시연영상 제작`

  <br><br><br>

---

### 2. 사용자WORKFLOW 및 기능소개(동영상)

**2-1. 사용자 WORKFLOW**

![SmartWarehouse_%20IoT/workflow.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F877542fe-07ef-4248-9f73-27904d7d7ffc%2Fworkflow.png?table=block&id=2f6bebf4-9bef-4566-a27e-7275f5854f49&width=1440&cache=v2)

i) ERP(Web)를 통한 재고 입·출고 주문`작업 송신`<br>ii) Tablet에서 `작업 수신` 및 대기중인 지게차에 `작업 할당`<br>iii) 지게차는 `작업 수행` 및 주기적으로 Tablet에 자신의 `상태 전송`<br>iv) Tablet에서 지게차 `실시간 상태 확인` 및 ERP로 지게차의 주요 `운용·상태 정보 송신`<br>v) ERP에서 창고별 `지게차 상태` 및 창고 `운영 내역` 확인

<br><br>

**2-2. [🎬YouTube에서 서비스 시연연상 보기](https://youtu.be/eaqHH8ln0Bo)**

<br><br><br>

---

### 3. **시스템 구성도 (및 기술스택, ERD)**

**3-1. 시스템 구성도**

![SmartWarehouse_%20IoT/Untitled.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fc90fc0aa-3eb8-4965-ae51-f69ad2209db7%2FUntitled.png?table=block&id=fe085d82-e57a-4f3b-b90c-4a5d8564f012&width=2180&cache=v2)

<br><br>

**3-2. 기술스택**

![SmartWarehouse_%20IoT/skill_specification.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F030aec96-60bd-4606-b316-ff56e91a876f%2Fskill_specification.png?table=block&id=7c7b255b-bdf1-4d27-805b-a361598270df&width=3070&cache=v2)

<br><br>**3-3. ERD**

![SmartWarehouse_%20IoT/erd.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F8ea4fc73-0e05-4ae0-9d6d-4e4c000a48d7%2Ferd.png?table=block&id=1cc1eb40-7bab-43d7-858c-3874da985c65&width=2370&cache=v2)



<br><br><br>

---

### 4. **시스템 컴포넌트별 상세**

**4-1. ERP (Web Application Server)**

① 최초 접속화면(로그인)

![SmartWarehouse_%20IoT/WebApp_loginpage.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F41ef05ac-2a99-4903-8386-aec69a905444%2FWebApp_loginpage.png?table=block&id=ca2ec697-9b2a-41eb-8224-ab8303c09eea&width=2560&cache=v2)

직원 등록은 관리자 전용페이지에서 가능 ~~(회원가입 기능X)~~

<br>② 재고관리 화면 (상품 등록 / 입·출고 주문 및 내역 조회)

![SmartWarehouse_%20IoT/WebApp_itempage.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F36b4c55d-3b58-474b-9960-79c1dbd28262%2FWebApp_itempage.png?table=block&id=ee6962fd-0e66-4a4f-9674-b64ecc78843e&width=2530&cache=v2)

i)상품 등록<br>ii)상품 조회<br>iii)상품 발주<br>iv)발주 내역 조회

<br>③ 지게차 관리 화면

![SmartWarehouse_%20IoT/WebApp_forkliftpage.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fe28dde5e-f309-4a8f-aa78-5823cbe5e437%2FWebApp_forkliftpage.png?table=block&id=c68d0785-3aad-4249-b25f-8f62b0ccf889&width=2530&cache=v2)

i) 최근 일주일간 지게차 주행 거리<br>
ii) 지게차 상태(실시간으로 반영)

<br>④ 재고 배치 효율화 솔루션 제공 화면

![SmartWarehouse_%20IoT/solution.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F800d441d-8658-4463-81ac-f28ce599b360%2Fsolution.png?table=block&id=d1af7d26-29b5-4859-91da-47abe966a8ef&width=3780&cache=v2)

- HeattMap으로 시각화 : 선택한 기간동안의 창고 상품 좌표별 입출고 건수
- 입출고 건수가 높은 상품을 창고 입구 가까이에 배치<br>
→ 동일한 상품 입출고에 대한 지게차 이동거리 감소<br>
ex) 10m*2회+30m*10회=320m → 10m*10회+30m*2회=160m ***(50%감소)***
- Hadoop / R<br>
i) 창고 입출고 내역 및 지게차 이동거리 Log를 Hadoop Server에 저장<br>
ii) Log전처리(Hadoop) 및 Heatmap(R)사용 창고 내 상품별 입출고 빈도수 시각화<br>
iii) 상품위치 변경하여 지게차 이동거리가 최소화되는 알고리즘 구현<br>

<br>⑤ (관리자 전용) 직원 관리 화면

![SmartWarehouse_%20IoT/WebApp_employeepage.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fdd429f67-b5aa-4326-b2bb-a101bb78fee1%2FWebApp_employeepage.png?table=block&id=84aaacf1-f508-45b3-8567-cb841cacbbf8&width=2530&cache=v2)

관리자만 접근 가능한 직원관리 화면<br>
i) 창고별 직원수 통계<br>
ii) 직원 등록<br>
iii) 직원 조회

<br><br>**4-2. TCP/IP Server**

- ERP(Web)와 창고별 태블릿간 네트워크 중개
- 새로운 태블릿만 새로 TCP/IP에 연동시, 시스템 변경 없이 창고 추가 가능<br>
**Thread Pool을 사용한 구현으로, 안정적 네트워크 지원*

<br><br>**4-3. 창고 DASHBOARD (Android Tablet)**

![SmartWarehouse_%20IoT/tabletServer.jpg](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fe0196ba9-993a-4411-a0bd-6c91c5774e9b%2FtabletServer.jpg?table=block&id=9ed03c8c-068f-4bc9-bf3f-ea7ad8098a03&width=1670&cache=v2)

i) 지게차 현재 위치 표시 (1초 주기 갱신)

ii) 지게차별 상태 (1초 주기 갱신)<br>
    **WAITING** · **WORKING** · **CHARGING** / 현재작업 / 온도 / 배터리

iii) 주요 상태메세지<br>
    네트워크 연결 상태 / 작업 할당 / 지게차 상태변화 등

iv) ERP(Web)로 부터 수신한 입출고 작업 목록

<br><br>

**4-4. 지게차 (TCP/IP · CAN Protocol)**

![SmartWarehouse_%20IoT/fl.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F4d91494f-feab-4b67-b54a-9bc945b7be93%2Ffl.png?table=block&id=4712b9cc-b30e-4ae6-a527-38932186106c&width=1220&cache=v2)

- **LattePanda, CanPro를 이용한 지게차 구현** <i>*프로젝트 환경</i><br>
  i) TABLET과의 외부 통신은 TCP/IP<br>
  ii) 지게차 **내부** 통신은 **Can Protocol** 사용<br>

- **CONTROLLER**<br>
i) TABLET으로부터 입출고 작업 수신(상품 위치, 수량 등)<br>
ii) 1초 주기로 각 ECU의 상태를 TABLET으로 송신
- **ECU①** Location : 현재 위치
- **ECU②** Battery<br>
i) Battery 30% 미만시 `CHARGING`<br>
ii) 작업 수신 시 `WAITING` → `WORKING`<br>
iii) 작업 완료 시 `WORKING` → `WAITING`<br>
**상태별로 배터리 증가/감소 속도 차이*
- **ECU③** Temperature : 현재 온도

<br><br><br><br><br>