# Android Custom Snackbar

[![](https://jitpack.io/v/kishandonga/custom-snackbar.svg)](https://jitpack.io/#kishandonga/custom-snackbar)

Android custom snackbar is derived from the android default snackbar control  
- Use it in Activity or Fragment
- Change the background and text color easily
- Custom view applies in the snackbar control
- Typeface applies easily on text
- Corner radius and Padding applies
- Androidx support
- More to find out yourself

## Installation
Gradle:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.kishandonga:custom-snackbar:1.0'
}

//required support lib modules
implementation 'androidx.appcompat:appcompat:1.1.0-alpha02'
implementation 'com.google.android.material:material:1.1.0-alpha03'
```

## Screenshots

<div>
Custom snackbar same as Default Control of the android Snackbar<br/> 
<img width="441" height="150" src="images/img_1.png"/><br/><br/> 
Make background semi-transparent and add corner radius for the better look<br/> 
<img width="441" height="150" src="images/img_2.png"/><br/><br/>  
Custom view according to your requirements<br/> 
<img width="441" height="105" src="images/img_3.png"/><br/><br/>  
</div>

## Examples

#### Refer This Sample Code
For, the more infromation refer this sample code [here](app/src/main/java/com/example/custom_snackbar/ui/)

#### With Coordinator Layout
Passing the activity context and coordinator layout as view in the custom snackbar constractor as shown below
```kotlin
CustomSnackbar(this, **<root_view>**).show {...}
```

#### Without Coordinator Layout
Only pass the activity context in the custom snackbar constractor as shown below
```kotlin
CustomSnackbar(this).show {...}
``` 
Kotlin
```kotlin
CustomSnackbar(this, root).show {
    textTypeface(BOLD | BOLD_ITALIC | ITALIC | NORMAL | CUSTOM)
    actionTypeface(BOLD | BOLD_ITALIC | ITALIC | NORMAL | CUSTOM)
    textColor(...)
    backgroundColor(...)
    border(..., ...)
    cornerRadius(...)
    padding(...)
    duration(LENGTH_INDEFINITE | LENGTH_LONG | LENGTH_SHORT)
    actionTextColor(...)
    message(...)
    withAction(android.R.string.ok) {
        it.dismiss()
    }
}
```

```java
```

#### Custom View

Kotlin
```kotlin
CustomSnackbar(this).show {
    customView(R.layout.snack_layout)
    padding(...)
    duration(LENGTH_INDEFINITE | LENGTH_LONG | LENGTH_SHORT)
    withCustomView {
        it.findViewById<View>(R.id.btnUndo).setOnClickListener {
            dismiss()
        }
    }
}
```

```java
```

## API Documents

|Function             |Parameter            |Description          |
|:-------------------:|:-------------------:|:-------------------:|
|actionTextColor      | Integer Color Value | Change the action button text color, default value is colorAccent
|actionTextColorRes   | Color Resource      | Change the action button text color, default value is colorAccent
|textColor            | Integer Color Value | Change the message text color, default value is white color
|textColorRes         | Color Resource      | Change the message text color, default value is white color
|backgroundColor      | Integer Color Value | Change the background color of the snackbar, default value is same as snackbar background also you can't make it transparent
|backgroundColorRes   | Color Resource      | Change the background color of the snackbar, default value is same as snackbar background also you can't make it transparent
|cornerRadius	      | Float Value         | Apply corner radius all the side (Left, Top, Right, Bottom), default value is 0
|cornerRadiusRes      | Dimension Resource  | Apply corner radius all the side (Left, Top, Right, Bottom), default value is 0
|border		          | Width as Integer and Integer Color Value   | Apply border width and color around the snackbar, default value of the width is 0 and color is transparent
|borderRes            | Width as Dimension Resource and Color Resource  | Apply border width and color around the snackbar, default value of the width is 0 and color is transparent
|customView           | View or Layout Resource  | set the your customized view as snackbar, default value is null view
|message              | String  | set the message as string and default value is empty string
|messageRes           | String Resource  | set the message as string and default value is empty string
|duration             | Integer Value  | set the time duration default value is Snackbar.LENGTH_SHORT
|padding              | Integer Value  | apply the padding at the (Left, Right, Bottom) side, default value is 0
|paddingRes           | Dimension Resource  | apply the padding at the (Left, Right, Bottom) side, default value is 0
|textTypeface         | Typeface  | Change the message text Typeface, default value is Typeface.NORMAL
|actionTypeface       | Typeface  | Change the action button text Typeface, default value is Typeface.NORMAL
|withAction           | String Resource or String and Snackbar as anonymous function | pass the first argument as action button name and default value is empty string, second argument as lamda function with snackbar reference
|withCustomView       | View as anonymous function  | when custom view initialze then passing here for your further use
|show	              | Void Or Koltin DSL | Show the snackbar view
|dismiss              | Void Or Unit | Dismiss the snackbar view
|getView              | Void and return View? | Same as withCustomView but it will return null value too





