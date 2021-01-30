package com.example.usersapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.room.PrimaryKey
import com.example.usersapp.data.User
import com.example.usersapp.data.UserViewModel
import com.example.usersapp.ui.adapter.UserAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class Obj(contentLayoutId: Int) : Fragment(contentLayoutId) {
    private val ADD_USER_REQUEST_CODE = 1

    //private static final int EDIT_USER_REQUEST_CODE = 2;
    //database
    var viewModel: UserViewModel? = null

    //widget
    var recyclerView: RecyclerView? = null
    var fab: FloatingActionButton? = null
    var userAdapter: UserAdapter? = null


    //Nav Drawer
    var navigationView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null
    var toolbar: Toolbar? = null

    //Buttom Nav
    var bottomNavigationView: BottomNavigationView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.btm_nav)


    fun main(args: Array<String>) {
        val name: String = "kolade";
        var age: String? = "21"
        var lenth :Int? = 2

        if (age == null){
            lenth =  null
        }

        println(name)

        age = null;

        fun sendDetails (message : String, date :String ): String{
            return message
        }

        println(sendDetails("Kolade is the main man and he is a good man to the call", "name"))



    class User(val firstName: String, val lastName: String, val email: String, val dateOfBirth: String, val phone: String, val dateAdded: String) {
        @PrimaryKey(autoGenerate = true)
        var id = 0

    }
    class Gym(var details: String, var image: Int, var plan: String){

    }

    val bend = Gym("This is the details",200, "The Bend")
        println(bend.details + " "+ bend.image+ " "+bend.plan);

        when(bend.image){
            in 100..200-> println("small range")
            in 201..400-> println("medium  range")
            in 401..600 ->println("Large  range")
            else -> println("inavlid range")
        }

        //function
        fun greeting(sender:String,receiver:String,message:String,date:Int):String{

            return " This $message was send to $receiver by $sender on $date"
        }
        println(greeting(
                "Mr Kolade",
                "Pastor Kumuyi",
                "Daddy, you are wonderful sir, very happy to have and learn from you sir, you'll not miss heaven in Jesus name",
                12/10/2000
        ))


    }
        class Student(var name:String,var email:String,var phone:String,var gender:String,val dept:String,var level:Int,var cgpa:Double){
            constructor(namew:String, course:String)
        }

        val ebenezer = Student("Kolade Oluwadare","kolade4jesus@gmail.com","08111","Male","CPE",400, 5.00)
        println(ebenezer.cgpa)
        ebenezer.phone = "08104"

        var Bola = Student("Bollll","mmte")
}


    internal interface kolade
    internal interface tope
    internal interface goodluck

    internal class sample(firstName: String?, lastName: String?, email: String?,
                          dateOfBirth: String?, phone: String?, dateAdded: String?) :
            User(firstName, lastName, email, dateOfBirth, phone, dateAdded), kolade, tope, goodluck {
        var sample1 = sample("", "", "", "", "", "")
    }


}