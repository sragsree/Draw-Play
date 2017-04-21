# Draw & Play - A stress reliever app

The days of simply swiping through the menu of your smartphone when you are stressed out is finally over. Draw & play is an android(JAVA) application that allows you to draw circles of different sizes on your mobile screen and set them into motion. The app basically has 3 modes 

  - Insert
  - Delete
  - Move

Insert mode allows you to draw circles of different sizes on the screen, Delete mode deletes the circles on touch, Move mode sets the circles in motion in the direction and velocity with which you swipe it.The circles collide at the edge of the screen and bounces applying the laws of physics.There can be multiple cirlces in motion at the same time.They even collide each other and retraces their paths.
### Installation

The application can be run using the apk on any device running android 4.3 and above
For contibuting to the development or to view the source code one has to install Android Studio. The installation is as follows

  - Download Android Studio from https://developer.android.com/studio/index.html
  - Launch the .exe file you downloaded.
  - Follow the setup wizard to install Android Studio and any necessary SDK tools.
  - This application can be run in Emulator or on an actual device running Android 4.4 or above.

### Usage

```
public AnimatedView(Context context, AttributeSet attrs)  {
                super(context, attrs);
                mContext = context;
                h = new Handler();
      }
        private Runnable r = new Runnable() {
                 @Override
                 public void run() {
                         invalidate();
                 }
         };
```		 

### Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

License
----

**Free Software, Hell Yeah!**
