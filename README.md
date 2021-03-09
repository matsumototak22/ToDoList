# ToDoList

## 仕様（制約事項）
- 認証認可にはFirebaseAuthのメールパスワード認証を用いる
- 認証まわりについては、Firebase-UIは使用せず自身で実装
- TODOリストのデータ管理にはCloudFireStoreを用いる
- アーキテクチャを適用する

## 仕様（実装する機能）
- 起動時にログイン画面に遷移
- ログイン後、ログアウト可能
- ToDoを登録できる
- 登録するToDoの内容は、30文字以内
- 登録した内容をリストとして表示する
- ToDoに"完了"、"未実施"の区分を持たせる
- ログインユーザーが切り替わった際に、直前のユーザーのリストを表示しない

## DB
- todo（String,30文字）
- complete（Boolean）
- createdAt（date）

## 開発フロー
1. FirebaseAuthのキャッチアップ（2/16）
2. CloudFireStoreのキャッチアップ（2/17）
3. アーキテクチャの検討（2/18）
4. 実装方針の検討（2/18~2/19）
5. 開発（2/20~2/24）
6. テストケースの作成（2/25）
7. テスト（2/26）
8. 戻り修正（2/27）
9. テスト（2/28）
10. 提出（3/1）


## FirebaseAuth
https://firebase.google.com/docs/auth?hl=ja
アプリでユーザーIDを識別することにより、ユーザーデータをクラウドに安全に保存したり、ユーザーのすべてのデバイスで、カスタマイズされた同じ体験を提供可能

https://firebase.google.com/docs/auth/where-to-start?hl=ja

https://github.com/firebase/quickstart-android/tree/e8743a69ae3e21b66414fe9890b0dffaac20ff1d/auth/app/src/main/java/com/google/firebase/quickstart/auth/kotlin


## CloudFireStore
https://firebase.google.com/docs/firestore?hl=ja


## アーキテクチャ
https://eh-career.com/engineerhub/entry/2018/01/17/110000
- MVVMの適用
- 


MVVM＆FireStore
https://stackoverflow.com/questions/54466799/retrieving-data-from-firestore-using-mvvm-architecture

MVVM&ToDoList
https://tokku-engineer.tech/todomvvmkotlin/
