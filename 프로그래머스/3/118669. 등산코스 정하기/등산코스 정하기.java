/*
<떠올렸던 풀이 방법>
-dfs, 동선 저장이 필요하고 동선에 대한 시간 값을 가져와야 함
-낮은 산봉우리를 찾고 동선에 포함이 되어있는지를 판단해야 함
-간선 사이의 최소값을 갱신하면서 탐색한다

<다른 사람의 풀이 참고 후 알게된 방법>
-다익스트라 알고리즘

시간 : 14:35 - 제한 15:35
다른 사람의 풀이 참고
(https://codingwell.tistory.com/174, https://ksb-dev.tistory.com/75)

<보완이 필요했던 부분>
그래프에 대한 이해와 관련 알고리즘 공부

A-?-…-?-B-?-…-?-A 와 같은 등산코스에서 A를 제외한 모든 지점에서 휴식을 취한다고 생각하면
경로에 포함된 등산로들의 w값 중 최댓값이 등산코스의 intensity가 됩니다.
또한, intensity가 최소가 되도록 A에서 B로 가는 길을 찾았다면
B에서 A로 돌아올 때도 똑같은 길을 그대로 돌아오면 intensity가 최소가 됩니다.
따라서, 출입구-산봉우리-출입구 경로의 최소 intensity를 찾는 대신,
출입구-산봉우리 경로의 최소 intensity만 찾아도 문제를 해결할 수 있습니다.
->양방향인 등산로를 단방향으로 생각하고 처리하면 규칙을 지킬 수 있음
(출처 : https://tech.kakao.com/2022/07/13/2022-coding-test-summer-internship/)
*/
import java.util.*;
class Solution {
    private static class Node {
        int e, w; // 노드, 가중치

        Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
    
    private int result = Integer.MAX_VALUE, minIntensity = Integer.MAX_VALUE;
    private List<Node>[] graph;
    private int[] intensity;
    private Set<Integer> gateSet = new HashSet<>(), summitSet = new HashSet<>();

    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int gate : gates) {
            gateSet.add(gate);
        }

        for (int summit : summits) {
            summitSet.add(summit);
        }

        for (int[] path: paths) {
            int s = path[0];
            int e = path[1];
            int w = path[2];

            if (gateSet.contains(s) || summitSet.contains(e)) {
                graph[s].add(new Node(e, w));
            } else if (gateSet.contains(e) || summitSet.contains(s)) {
                graph[e].add(new Node(s, w));
            } else {
                graph[s].add(new Node(e, w));
                graph[e].add(new Node(s, w));
            }
        }

        dijkstra(n, gates, summits);
        return new int[]{result, minIntensity};
    }

    private void dijkstra(int n, int[] gates, int[] summits) {
        Queue<Node> queue = new ArrayDeque<>();
        intensity = new int[n + 1];

        Arrays.fill(intensity, Integer.MAX_VALUE);

        for (int gate : gates) {
            queue.add(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.w > intensity[node.e]) continue;

            for (Node next : graph[node.e]) {
                int dis = Math.max(intensity[node.e], next.w);

                if (intensity[next.e] > dis) {
                    intensity[next.e] = dis;
                    queue.add(new Node(next.e, dis));
                }
            }
        }

        Arrays.sort(summits);

        for (int summit : summits) {
            if (minIntensity > intensity[summit]) {
                minIntensity = intensity[summit];
                result = summit;
            }
        }
    }
}