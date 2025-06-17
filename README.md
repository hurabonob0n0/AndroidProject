SmartPhone Game Programming

게임 제목 : Portal BounceBall

게임 컨셉 :

![image](https://github.com/user-attachments/assets/bfaf109c-e2ec-47eb-9eef-c1c1d99ebd8e)


유명한 게임인 바운스볼과 포탈의 기믹을 합친 게임이다.

개발 범위:
![image](https://github.com/user-attachments/assets/2f880073-3802-4a8c-8fad-089115928828)


시작화면 – 레벨 선택 단계 – 인게임, 이렇게 3단계의 Scene 구현 안드로이드 스튜디오의 레이아웃을 활용하여 시작 화면을 구현한다. 레벨 선택 단계와 인게임은 커스텀 뷰를 사용하여 구현한다. Scene 간의 입력 처리와 상호작용 구현한다.

2D 상의 물리를 구축 중력, 포탈을 탔을 때 속도의 방향이 바뀌는 것, 충돌처리, 더블점프 등을 구현한다.

5가지의 다른 기믹을 활용할 수 있는 스테이지 구현 스테이지를 5개 만들어서 여러 기믹을 사용할 수 있도록 구현한다.

추가로 시간이 남는다면 맵툴을 만든다.

진행 상황:

Scene 구현 80%

2D 상의 물리 구축 50%

5가지 다른 기믹을 활용할 수 있는 스테이지 구현 0%

맵툴 구현 50%\

주차별 Commit 상황 
![image](https://github.com/user-attachments/assets/2c86005f-6102-4cf8-8458-3d92e2ff328d)


사용된 기술:

교수님이 만들어주신 FrameWork 내에서 대부분 사용하였다.

박스 충돌, sprite, Scene 등등


아쉬운 부분:

중간에 졸작을 한다고 수업 흐름을 놓쳤는데 끝까지 제대로 따라가지 못한 것 같아서 아쉽다.

판다면 클리어 조건인 별과 map 크기를 더 키워야 할 것 같고 원래 하려 했던 portal과 item들을 추가해야 할 것 같다.

또한 정말 시간을 충분히 가지고 만든다면 현재는 충돌처리를 모든 Tile을 순회하며 하지만 (한 프레임에 최대 충돌횟수는 2이기 때문에 두 번 충돌하면 순회를 끝나게 해놓긴 했다) 

Ball의 위치에 따라 충돌할 수 있는 4개의 tile에 대해서만 충돌처리를 할 수 있도록 수정하고 싶다.

수업을 통해 얻은 것:

게임 프레임워크를 왜 만들고 어떤 식으로 만드는 지 배운 것 같다.

발표영상 : https://youtu.be/YP5f8lfHYG4

시연 영상 : https://youtu.be/93CGHKIOS1c
