# PrescripXionMerged_V1

1) Login Activity : 
  First page of the app. Contains options to login with correct user info or signup for new users.
  If the user provides correct information and logs in then it takes the user to the MainActivity.
  If new user wants to sign up then it takes the user to the SignUpForm1 & SignUpForm2.
  
2) SignUpForm1 & SignUpForm2 : 
  Takes necessary information from new users to sign up.
  
3) MainActivity : 
  The main page with searchbar and bottom navigation bar from which the user orders/buys medicines implemented with recycler view.
  ProfileActivity, CameraActivity, CartActivity, MedicationActivity are connected with this page.
  
4) ProfileActivity : 
  Displays user info and previous orders or frequently ordered medicines from the MedicationActivity.
  
5) CameraActivity : 
  Captures the picture of prescription and stores it in local storage.
  
6) CartActivity :
  Displays the ordered medicines and update the orderlist and confirm purchase,
