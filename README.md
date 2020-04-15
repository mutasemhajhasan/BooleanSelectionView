# BooleanSelectionView
Android widget to use for Boolean selection like Gender, Martial status, On Off...etc

<img src="https://raw.githubusercontent.com/mutasemhajhasan/BooleanSelectionView/master/demo.gif" />

# Usage
## Gradle
```gradle
allprojects {
	repositories {
			...
		maven { url 'https://jitpack.io' }
	}
}
dependencies {
	  implementation 'com.github.mutasemhajhasan:BooleanSelectionView:1.2.0'
}
```
## activity.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="10dp"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="Boolean selection view"
        android:textSize="20sp"
        android:textStyle="bold" />

    <me.mutasem.booleanselection.BooleanSelectionView
        android:id="@+id/gender"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="50dp"
        app:endText="Female"
        app:selectedColor="#7C4949"
        app:startText="Male"
        app:textColor="#FFFFFF"
        app:unSelectedColor="#BA7979" />

    <me.mutasem.booleanselection.BooleanSelectionView
        android:id="@+id/martial"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:layout_marginTop="50dp"
        android:padding="5dp"
        app:endText="Married"
        app:selectedColor="#009688"
        app:startText="Single"
        app:textColor="#FFFFFF"
        app:unSelectedColor="#2B534F" />

    <me.mutasem.booleanselection.BooleanSelectionView
        android:id="@+id/onOff"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:layout_marginTop="50dp"
        android:paddingLeft="10dp"
        android:paddingRight="10dp"
        app:endText="On"
        app:selectedColor="#673AB7"
        app:startText="Off"
        app:textColor="#FFFFFF"
        app:unSelectedColor="#321F55" />
</LinearLayout>
```
## activity.java
```java
BooleanSelectionView gender, martial, onOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gender = findViewById(R.id.gender);
        martial = findViewById(R.id.martial);
        onOff = findViewById(R.id.onOff);
        gender.setSelectionListener(new BooleanSelectionView.SelectionListener() {
            @Override
            public void onSelectionChanged(int selectionIndex, String selectedText) {
                //selection index is one of tow values:
                // (BooleanSelectionView.Selection.End or BooleanSelectionView.Selection.Start)
                Toast.makeText(MainActivity.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        });
        martial.setSelectionListener(new BooleanSelectionView.SelectionListener() {
            @Override
            public void onSelectionChanged(int selectionIndex, String selectedText) {
                Toast.makeText(MainActivity.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        });
        onOff.setSelectionListener(new BooleanSelectionView.SelectionListener() {
            @Override
            public void onSelectionChanged(int selectionIndex, String selectedText) {
                Toast.makeText(MainActivity.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        });
        // you could use the getSelection() to get the current selection
        // the return value is one of tow values:
        // (BooleanSelectionView.Selection.End or BooleanSelectionView.Selection.Start)
    }
 ```
 
