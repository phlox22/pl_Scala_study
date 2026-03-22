import scala.collection.mutable

// 가변 커스텀 노드 클래스
class Node(var car: Any = null, var cdr: Any = null)

object hw1_pair_assignments {
  //--- Exercise 1: c++(문제 코드) -> scala ---
  def ex1_count_pairs(x: Any): Int = { //int 반환
    x match { //패턴 매칭 - c++의 switch문과 유사
      case p: Node => // x가 Pair 타입인지 확인
        ex1_count_pairs(p.car) + ex1_count_pairs(p.cdr) + 1  // car와 cdr을 재귀적으로 방문하고 자신을 더함
      case _ => 0 //Pair가 아닌 경우(null 포함)
    }
  }

  // --- Exercise 2: 고유한 쌍 개수 세기 ---
  def ex2_count_pairs(x: Any): Int = {
    val visited = mutable.Set[Node]() //이미 방문한 쌍들을 추적하여 기록해두는 보조 자료 구조 (Set)
    def count(item: Any): Int = item match {
      case p: Node => //item이 Node 타입이면
        if (visited.contains(p)) 0 //방문했으면 0 반환 (중복 방지)
        else {
          visited.add(p) //방문 안했으면 방문한 거 기록 후 개수 세기
          1 + count(p.car) + count(p.cdr)
        }
      case _ => 0
    }
    count(x)
  }

  // --- Exercise 3: 순환 탐지 ---
  def ex3_check_cycle(x: Any): Boolean = { //무한 루프에 빠지게 될지 Boolean으로 반환
    val visited = mutable.Set[Node]() //방문한거 기록해주는거 (보조 자료 구조)
    def check(item: Any): Boolean = item match { //무한루프에 빠질지 체크
      case p: Node => //item이 Node 타입이면
        if (visited.contains(p)) true //방문한 노드인지 확인 => 방문한 노드이면 순환
        else { //방문한 적 없는 노드이면
          visited.add(p) //방문한 거 기록
          check(p.cdr) //다음 노드 가서 또 체크
        }
      case _ => false
    }
    check(x)
  }

  // --- Exercise 4: 상수 공간 순환 탐지 ---
  def ex4_check_cycle(x: Any): Boolean = {
    //보조 자료 구조에 저장 없이 해결
    var slow: Any = x
    var fast: Any = x

    //순환이 있으면 fast가 slow를 따라잡을 것
    while (fast != null) { //fast가 이동할 때 null이 있으면 false (순환이 있으면 null이 나올 수 없기 때문)
      fast match {
        case f: Node => //fast가 Node 타입인 경우
          val next = f.cdr // fast의 다음 노드
          next match {
            case n: Node =>
              fast = n.cdr //fast의 다음 다음 코드 (2개 이동)
              slow = slow.asInstanceOf[Node].cdr //(slow는 한개 이동)
            case _ => return false
          }
        case _ => return false
      }
      if (slow == fast) return true //같으면 fast가 slow 따라잡음 (순환)
    }
    false
  }
}