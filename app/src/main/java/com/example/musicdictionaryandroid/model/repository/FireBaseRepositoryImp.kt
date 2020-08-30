package com.example.musicdictionaryandroid.model.repository

// class FireBaseRepositoryImp {
//
//    companion object {
//        private const val TAG = "FireBaseRepositoryImp"
//    }
//
//    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
//
//
//    //ユーザーのEmailを取得
//    override fun getEmail(): String{
//        return auth.currentUser!!.email!!
//        return ""
//    }
//
//    //自動ログイン認証
//    override fun firstCheck(onSuccess: () -> Unit, onError: () -> Unit) {
//        if(auth.currentUser != null) {
//            onSuccess() //こんな感じで置き換える
//        } else {
//            onError()
//        }
//    }
//
//    //ログイン機能
//    override fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit) {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener{ task ->
//                if (task.isSuccessful) {
//                    Log.d(TAG, "ログイン成功")
//                    onSuccess()
//                    //  callBack.success()
//                } else {
//                    Log.d(TAG, "ログイン失敗", task.exception)
//                    onError()
//                    // callBack.failure()
//                }
//            }
//
//    }
//
//    //ログアウト
//    override fun signOut(onSuccess: () -> Unit, onError: () -> Unit) {
//        auth.signOut()
//        if(auth.currentUser == null){
//            Log.d(TAG, "ログアウト作成成功")
//            onSuccess()
//        }else {
//            Log.d(TAG, "ログアウト失敗")
//            onError()
//        }
//    }
//
//    //アカウント作成
//    override fun signUp(email:String, password:String, onSuccess: () -> Unit, onError: () -> Unit) {
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener{ task ->
//                if (task.isSuccessful) {
//                    Log.d(TAG, "アカウント作成成功")
//                    onSuccess()
//                } else {
//                    Log.w(TAG, "アカウント作成失敗", task.exception)
//                    onError()
//                }
//            }
//    }
//
//    //ユーザー削除
//    override fun delete(onSuccess: () -> Unit, onError: () -> Unit){
//        auth.currentUser!!.delete()
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Log.d(TAG, "ユーザーを削除しました")
//                    onSuccess()
//                }else{
//                    Log.d(TAG, "予期せぬエラーが発生しました")
//                    onError()
//                }
//            }
//    }
//
// }
