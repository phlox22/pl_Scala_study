// C++로 작성된 코드 중 struct Pair 부분에 해당
class Pair(var car: Any = null, var cdr: Any = null)

@main def runEx1(): Unit = {
  // C++의 count_pairs(Pair* x) 함수
  def countPairs(x: Any): Int = {
    x match { //패턴 매칭 - c++의 switch문과 유사
      case p: Node => // x가 Pair 타입인지 확인
        countPairs(p.car) + countPairs(p.cdr) + 1  // car와 cdr을 재귀적으로 방문하고 자신을 더함
      case _ => 0 //Pair가 아닌 경우(null 포함)
    }
  }
//
//  // --- Exercise 1: 3개의 쌍으로 구성된 구조들 ---
//
//  // 1. 3을 반환하는 경우 (일반적인 연결 리스트)
//  val p3_1 = new Pair('c', null)
//  val p2_1 = new Pair('b', p3_1)
//  val p1_1 = new Pair('a', p2_1)
//  println(s"1. Result 3 Case: ${countPairs(p1_1)}")
//
//  // 2. 4를 반환하는 경우
//  val p3_2 = new Pair('c', null)
//  val p2_2 = new Pair(p3_2, p3_2) // p3 객체를 car와 cdr이 공유함
//  val p1_2 = new Pair('a', p2_2)
//  println(s"2. Result 4 Case: ${countPairs(p1_2)}")
//
//  // 3. 7을 반환하는 경우
//  val p3_3 = new Pair('c', null)
//  val p2_3 = new Pair(p3_3, p3_3)
//  val p1_3 = new Pair(p2_3, p2_3) // p2와 p3가 모든 경로에서 공유됨
//  println(s"3. Result 7 Case: ${countPairs(p1_3)}")
//
//  // 4. 무한 루프에 빠지는 경우
//  println("4. Infinite Loop Case: 실행 시 StackOverflowError가 발생하므로 주석을 해제하여 확인하세요.")
//  val p3_4 = new Pair('c', null)
//  val p2_4 = new Pair('b', p3_4)
//  val p1_4 = new Pair('a', p2_4)
//  p3_4.cdr = p1_4 // 순환 구조 생성
//  // println(countPairs(p1_4))
}