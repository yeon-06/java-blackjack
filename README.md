# java-blackjack

## 블랙잭 용어 정리
- 버스트: 숫자 카드의 합이 21 초과
- 블랙잭: 숫자 카드 합이 21 + 카드 수가 2장
- 푸시: 딜러와 플레이어 카드 동점 (=비김)
- 스테이: 카드를 그만 받겠다
- 힛: 카드를 한장 더 받겠다

## 구현할 기능 목록

- [x] 참여할 사람의 이름 입력
    - 쉼표 기준으로 분리
    - (e) 빈 값, 공백 불가
    - (e) 중복 불가
- [x] 카드 분배
    - 딜러, 참가자에게 카드 2장씩 분배
    - 딜러의 카드 1장 출력
    - 참가자의 카드 2장 출력
    - 참가자의 카드 합이 21이면 게임 종료
- [x] 참가자 카드 추가 분배
    - 카드 합이 21 이상인 참가자는 턴 종료
    - 참가자 별로 한장의 카드 추가 여부를 입력 받기 (y/n)
    - 참가자가 카드 추가 여부 입력
        - y를 선택한 경우 카드 추가 분배
        - n를 선택한 경우 해당 참가자의 턴 종료
    - 참가자의 카드 목록 출력
- [x] 딜러 카드 추가 분배
    - 16 이하면 1장 추가 분배
    - 17 이상이면 턴 종료
- [x] 결과 출력
    - 카드 목록 및 총합 출력 (ex: 딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20)
    - 최종 승패 계산
    - 최종 승패 출력
        - 딜러 n승 n패 출력
        - 참가자 승/패 결과만 출력

## 기능 요구 사항

- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)