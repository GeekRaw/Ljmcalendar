# Ljmcalendar

## 1.1 软件需求及功能分析

### 1.1.1 软件需求

开发一款人情来往记录软件YouOwnMe。

1、首页实现Flash窗口

2、主界面显示日历，可以添加记录

3、收礼界面显示所有收礼记录

4、随礼界面显示所有随礼记录

### 1.1.2 软件功能分析

1、首页实现Flash窗口

​    Flash窗口显示软件加载界面

2、主界面显示日历，可以添加记录

​    主界面默认选中当天日期，点击悬浮按钮后，跳转到添加记录页面，添加记录页面可以选择“随礼”还是“收礼”，默认记账时间为选中的日期，还可以输入金额、送礼人名字和收礼缘由，添加完成后跳转到主界面，并实时更新当前选中日期的所有记录，日历下方显示当日的全部记录，可以切换不同的日期以查看该天的所有收礼随礼记录。

3、收礼界面显示所有收礼记录

​    显示所有的收礼记录，点击悬浮按钮后，跳转到添加记录页面。同时，对于每一条收礼记录，都可以实时进行增加、修改、删除等操作。

4、随礼界面显示所有随礼记录

显示所有的随礼记录，点击悬浮按钮后，跳转到添加记录页面。同时，对于每一条随礼记录，都可以实时进行增加、修改、删除等操作。

## 1.2 Activity设计

### 1.2.1 软件的Activity

软件的Activity主要由LoadingActivity、AddActivity、ShowBar、MainActivity构成。

1、LoadingActivity是加载页面，主要用于实现首页Flash窗口，显示软件Logo---“YOUOWNME”

2、MainActivity是启动activity，主要包含下导航栏、toolbar和FloatingActionButton等内容，homeFragement为下导航栏的默认启动项，所以app启动显示的首页就是主界面，即日历显示界面。

GiveFragement为

3、AddActivity是添加或修改记录的activity，主要是新建和编辑收礼或随礼记录，包含了记录类型、记账日期、金额、姓名、缘由等内容。

4、ShowBar是显示金额统计图的Activtiy，主要可以显示不同缘由的金额统计图和不同日期的金额统计图。

### 1.2.2 Activity迁移

1、打开软件首先会进入LoadingActivity，等待数秒后，将跳转到MainActivity。

2、点击MainActivity页面右下角的“+”号（FloatingActionButton），会跳转到AddActivity，跳转时intent传递当前选中日期(以便AddActivity设置默认记账时间)，在该页面中进行收礼或随礼记录的新建。

3、点击MainActivity下方的导航栏，可以跳转到随礼界面、收礼界面或日历主界面。其中在随礼界面和收礼界面中，点击任意一个记录，弹出的菜单栏选中新增，则会跳转到AddActivity，跳转时intent传递类型和之前日历选中的日期；选中修改，则也会跳转到AddActivity，跳转时intent传递当前选中记录的所有信息，包括类型、记账日期、金额、姓名、缘由等；选中缘由金额统计图或日期统计图，则会跳转到ShowBar，跳转时intent传递当前页面类型和所选择的统计图类型，以便在ShowBar接收，并显示相应的金额统计图。

## 1.1 软件需求及功能分析

### 1.1.1 软件需求

开发一款人情来往记录软件YouOwnMe。

1、首页实现Flash窗口

2、主界面显示日历，可以添加记录

3、收礼界面显示所有收礼记录

4、随礼界面显示所有随礼记录

### 1.1.2 软件功能分析

1、首页实现Flash窗口

​    Flash窗口显示软件加载界面

2、主界面显示日历，可以添加记录

​    主界面默认选中当天日期，点击悬浮按钮后，跳转到添加记录页面，添加记录页面可以选择“随礼”还是“收礼”，默认记账时间为选中的日期，还可以输入金额、送礼人名字和收礼缘由，添加完成后跳转到主界面，并实时更新当前选中日期的所有记录，日历下方显示当日的全部记录，可以切换不同的日期以查看该天的所有收礼随礼记录。

3、收礼界面显示所有收礼记录

​    显示所有的收礼记录，点击悬浮按钮后，跳转到添加记录页面。同时，对于每一条收礼记录，都可以实时进行增加、修改、删除等操作。

4、随礼界面显示所有随礼记录

显示所有的随礼记录，点击悬浮按钮后，跳转到添加记录页面。同时，对于每一条随礼记录，都可以实时进行增加、修改、删除等操作。

## 1.2 Activity设计

### 1.2.1 软件的Activity

软件的Activity主要由LoadingActivity、AddActivity、ShowBar、MainActivity构成。

1、LoadingActivity是加载页面，主要用于实现首页Flash窗口，显示软件Logo---“YOUOWNME”

2、MainActivity是启动activity，主要包含下导航栏、toolbar和FloatingActionButton等内容，homeFragement为下导航栏的默认启动项，所以app启动显示的首页就是主界面，即日历显示界面。

GiveFragement为

3、AddActivity是添加或修改记录的activity，主要是新建和编辑收礼或随礼记录，包含了记录类型、记账日期、金额、姓名、缘由等内容。

4、ShowBar是显示金额统计图的Activtiy，主要可以显示不同缘由的金额统计图和不同日期的金额统计图。

### 1.2.2 Activity迁移

1、打开软件首先会进入LoadingActivity，等待数秒后，将跳转到MainActivity。

2、点击MainActivity页面右下角的“+”号（FloatingActionButton），会跳转到AddActivity，跳转时intent传递当前选中日期(以便AddActivity设置默认记账时间)，在该页面中进行收礼或随礼记录的新建。

3、点击MainActivity下方的导航栏，可以跳转到随礼界面、收礼界面或日历主界面。其中在随礼界面和收礼界面中，点击任意一个记录，弹出的菜单栏选中新增，则会跳转到AddActivity，跳转时intent传递类型和之前日历选中的日期；选中修改，则也会跳转到AddActivity，跳转时intent传递当前选中记录的所有信息，包括类型、记账日期、金额、姓名、缘由等；选中缘由金额统计图或日期统计图，则会跳转到ShowBar，跳转时intent传递当前页面类型和所选择的统计图类型，以便在ShowBar接收，并显示相应的金额统计图。

## 1.3 开发过程说明

### 1.1.1 软件开发环境：Android Studio 4.0.1

### 1.1.2 软件项目结构

如下图所示，展示了软件项目的结构，在com.jnu.ljmcalendar里面，主要由两部分组成，第一部分是数据存储和处理部分，包括DataBank和Record类；在另一部分中，有三个Fragment文件，包括give、home、和receive，分别对应收礼、首页和随礼界面，此外，MainActivity负责显示主界面，调用不同的Fragment等，AddActvity负责修改和添加记录等，LoadingActivity负责加载界面的实现，ShowBar实现对不同数据的统计图显示，RecordAdapter为Record的适配器。

​                             								  <img src="https://ae02.alicdn.com/kf/H82637b46682b4f1aa447061d9e6c95fb6.png" alt="image.png" title="image.png" />

​    同时，除了主程序之外，还设计了不同界面的Test，如下所示：

<img src="https://ae04.alicdn.com/kf/Hbf376e97d6744bc3abef8ad6bcdf4a89l.png" alt="image.png" title="image.png" />

### 1.1.3 开发过程

1、导航栏：选择Navigation Drawer Activity模板，修改代码完成收礼、随礼、首页导航栏及跳转。

2、xml的页面开发：实现首页、ListView、新建记录页面。

3、Activity迁移和功能实现：完成activity的跳转和数据迁移，实现新建和编辑倒计时活动的一系列功能。

4、实现收礼、随礼页面记录的不同菜单选项，包括新增、修改、删除等。

5、实现ShowBar统计图的显示。

6、进一步美化ListView和其Item，以及页面的UI设计。