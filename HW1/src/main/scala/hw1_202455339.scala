import scala.collection.mutable

// [공통 구조] 과제 가이드의 힌트를 바탕으로 정의한 가변 객체 [cite: 55]
class Pair(var car: Any = null, var cdr: Any = null)

object PairAssignment {
  //--- Exercise 1: c++(문제 코드) -> scala ---
  def countPairs(x: Any): Int = {
    x match { //패턴 매칭 - c++의 switch문과 유사
      case p: Pair => // x가 Pair 타입인지 확인
        countPairs(p.car) + countPairs(p.cdr) + 1  // car와 cdr을 재귀적으로 방문하고 자신을 더함
      case _ => 0 //Pair가 아닌 경우(null 포함)
    }
  }

  // --- Exercise 2: 고유한 쌍 개수 세기 ---
  // 보조 자료구조(Set)를 사용하여 중복 카운팅을 방지함
  def countDistinctPairs(x: Any): Int = {
    val visited = mutable.Set[Pair]()
    def count(item: Any): Int = item match {
      case p: Pair =>
        if (visited.contains(p)) 0
        else {
          visited.add(p)
          1 + count(p.car) + count(p.cdr)
        }
      case _ => 0
    }
    count(x)
  }

  // --- Exercise 3: 순환 탐지 ---
  // cdr을 따라가며 무한 루프 여부를 판별함
  def hasCycle(x: Any): Boolean = {
    val visited = mutable.Set[Pair]()
    def check(item: Any): Boolean = item match {
      case p: Pair =>
        if (visited.contains(p)) true
        else {
          visited.add(p)
          check(p.cdr)
        }
      case _ => false
    }
    check(x)
  }

  // --- Exercise 4: 상수 공간 순환 탐지 ---
  def hasCycleOptimized(x: Any): Boolean = {
    var slow: Any = x
    var fast: Any = x

    while (fast != null) {
      fast match {
        case f: Pair =>
          val next = f.cdr
          next match {
            case n: Pair =>
              fast = n.cdr
              slow = slow.asInstanceOf[Pair].cdr
            case _ => return false
          }
        case _ => return false
      }
      if (slow == fast) return true
    }
    false
  }
}