package hr.foi.aitsg

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import hr.foi.database.DataViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.foi.aitsg.auth.getAllProjectUsers
import hr.foi.database.Project_user
import hr.foi.database.User

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun addUsersToProject(navHostController: NavHostController, dataViewModel: DataViewModel, project_id : String?) {
    var id_project = project_id!!.toInt()
    var usersOfProject by remember { mutableStateOf<List<User>>(emptyList()) }
    usersOfProject = getAllProjectUsers(dataViewModel, id_project)
    var deleteUser by remember { mutableStateOf(false) }
    Scaffold {
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "SearchUsers",
                Modifier
                    .size(50.dp)
                    .clickable {
                        navHostController.navigate("search-users/$project_id")
                    })
            Text(text = "Dodani korisnici",
                Modifier.height(30.dp),
                color = MaterialTheme.colorScheme.tertiary)
            if(usersOfProject.isNotEmpty()){
                usersOfProject.forEach(){
                    Row (modifier = Modifier
                        .padding(all = 5.dp)
                        .background(
                            MaterialTheme.colorScheme.tertiary,
                            RoundedCornerShape(10.dp)
                        )
                        .padding(5.dp)
                    )
                    {
                        Text(text = it.email)
                        Box (modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                dataViewModel.deleteProjectUser(Project_user(id_project, it.id_user!!))
                                val indexofdeletedUser = usersOfProject.indexOf(it)
                                usersOfProject.drop(indexofdeletedUser)
                                Log.e("ui", usersOfProject.toString())
                            },
                            contentAlignment = Alignment.BottomEnd)
                        {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "DeleteUserFromProject")
                        }
                    }
                }
            }
        }
    }
}