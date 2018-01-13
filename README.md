# NotEasyMusic
An Musical Structure.

01/13/18: UI change.Introduced ViewPager & Fragments, RecyclerView & CardView.

12/29/17: Use MeidaPlayer class to play a song in raw repertory and a touchable SeekBar to display/adjust the proress of the song. However, no lifecycle control in PLayingActivity was done currently and no further control of media playback was indroduced in the app. Hopefully, they can be implemented soon.

11/10/17：ListView applied in Local and Online Activity.

11/05/17: Initiate Commit.
1. The bottom bar in the App exists in every Activity except Playing Activity, it leads to duplicated codes in Java files. After searching on Google, I learnt that `Fragments` can deal with this situation by combining serveral layouts into one Activity. The course later will cover `Fragments`, so I leave this problem behind for now.
2. Some improvements can be done, such as ListView/RecycleView applied in Local Activity, using Android Pay API as Payment Activity, however due to time limitation, I have to put all this aside and maybe do it later. 

This is a training project in [Udacity's Android Basics Nanodegree program](https://www.udacity.com/course/android-basics-nanodegree-by-google--nd803).  
Check out this and other courses here: https://www.udacity.com/courses/all

This project is an Android App Structure for a music player app, unfortunately, it didn't implement any feature about a music player.  
The perpose of this project is to practise `OnClickListeners` and `Explicit Intent` in Android.

Note: The name of this app is inspired by [NetEase Music](https://music.163.com/).
