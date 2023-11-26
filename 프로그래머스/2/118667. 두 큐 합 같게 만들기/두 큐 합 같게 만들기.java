import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int maxCnt = (queue1.length + queue2.length) * 2; //모든 원소가 다 뒤바뀌었을 경우 = 처음과 같아지는 경우
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();
        long sum1 = 0;
        long sum2 = 0;
        
        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            sum1 += queue1[i];
            sum2 += queue2[i];
        } //합계산과 큐생성을 한번에 해준다
        
        if ((sum1 + sum2) % 2 != 0) return -1; //짝수가 아닌 경우 return
        
        while (sum1 != sum2) {
            if (answer > maxCnt) {
                return -1;
            }
            if (sum1 < sum2) {
                sum1 += q2.peek();
                sum2 -= q2.peek();
                q1.offer(q2.poll());
            } else {
                sum1 -= q1.peek();
                sum2 += q1.peek();
                q2.offer(q1.poll());
            }
            answer++;
        }
        
        return answer;
    }
}

/* 
처음 작성했던 코드

<떠올렸던 풀이 방법>
-원소의 합을 구한다
-같도록 만들어야 하는 수를 정한다 -> 반으로 나누어지지 않는 경우 -1
-추출하고 추가하는 작업을 반복한다 -> 모든 원소를 뒤바꿨는데 안되는 경우 -1
-합이 큰 큐에서 작은 큐로 원소를 이동
-반복할 때마다 횟수를 증가시킨다

시간 : 시작 13:30 - 제한 14:30
점수 : 46.7 (시간초과 및 실패)

<보완이 필요했던 부분>
함수화를 하더라도 효율적으로 필요한 경우에만 해야함
반복문 사용 시 종료 조건을 잘 파악하고 계산 과정에서 오버플로우 발생 가능성을 고려해야함

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int target = getTargetNumber(queue1, queue2);
        int sum1 = 0;
        int sum2 = 0;
        int i = 0;
        Queue<Integer> q1 = madeQueue(queue1);
        Queue<Integer> q2 = madeQueue(queue2);
        
        if (target != -1) {
            while (target != sum1 && target != sum2 && q1.size() > 0 && q2.size() > 0) {
                if (getSum(q1) < getSum(q2)) {
                    q1.offer(q2.poll());
                } else {
                    q2.offer(q1.poll());
                }
                sum1 = getSum(q1);
                sum2 = getSum(q2);
                i++;
            }
        } else {
            return target;
        }
        
        return i != queue1.length ? i : -1;
    }
    
    private int getTargetNumber(int[] queue1, int[] queue2) {
        int firstSum = Arrays.stream(queue1).sum();
        int secondSum = Arrays.stream(queue2).sum();
        int sum = firstSum + secondSum;
        
        if (sum % 2 == 0) {
            return sum / 2;
        }
        else {
            return -1;
        }
    }
    
    private Queue<Integer> madeQueue(int[] arr) {
        Queue<Integer> queue = new LinkedList<Integer>();
        
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        
        return queue;
    }
    
    private int getSum(Queue<Integer> queue) {
        int sum = 0;
        
        for (int q : queue) {
            sum += q;
        }
        
        return sum;
    }
}
*/