package ir.at.todolist.feature.addTask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ir.at.todolist.HIGH
import ir.at.todolist.LOW
import ir.at.todolist.MEDIUM
import ir.at.todolist.R
import ir.at.todolist.data.Task
import ir.at.todolist.ui.theme.ToDoListTheme

@AndroidEntryPoint
class AddTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)
                    Greeting2(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting2(viewModel : AddTaskViewModel) {

    val activity = (LocalContext.current as? Activity)

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize() , horizontalAlignment = Alignment.CenterHorizontally) {

        // Tool Bar
        Box(modifier = Modifier.height(56.dp)) {
            Text(
                text = "Add Task",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp) ,
                textAlign = TextAlign.Center ,
                color = MaterialTheme.colors.onBackground ,
                fontWeight = FontWeight.Bold ,
            )

            IconButton(
                onClick = { activity?.finish() } ,
                modifier = Modifier
                    .size(56.dp)
                    .padding(start = 20.dp, top = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "" , tint = Color.Black ,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(3.dp)
                )
            }
        }

        // Edite Text Title
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 40.dp, end = 40.dp),
            value = title,
            onValueChange = { title = it } ,
            label = {
                Text(text = "Title")
            } ,
            singleLine = true
        )

        // Edite Text Description
        OutlinedTextField(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .padding(top = 30.dp, start = 40.dp, end = 40.dp),
            value = description,
            onValueChange = { description = it } ,
            label = {
                Text(text = "Description")
            }
        )

        // Button Save Task
        Button(
            onClick = {
                if (title.isEmpty()) Toast.makeText(activity?.baseContext , "Title is empty" , Toast.LENGTH_SHORT).show()
                else {
                    val task = Task(null , title , description , false)
                    viewModel.addTask(task)
                    activity?.finish()
                }
            } ,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 50.dp, start = 40.dp, end = 40.dp),
        ) {
            Text(text = "SAVE")
        }
    }
}