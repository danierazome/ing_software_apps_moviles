package com.example.vinilos.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vinilos.R
import com.example.vinilos.ui.enumIU.UserType
import com.example.vinilos.ui.enumIU.VinylsScreen

@Composable
fun Login(
    modifier: Modifier = Modifier,
    updateUser: (String) -> Unit = {},
    navigateTo: (String) -> Unit = {}
) {

    Column {
        Text(
            text = stringResource(id = R.string.login_header),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 50.dp, end = 80.dp, bottom = 100.dp)
        )
        Image(
            painter = painterResource(R.drawable.login),
            contentDescription = "Login Vector",
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick =
            {
                updateUser(UserType.Visitor.name)
                navigateTo(VinylsScreen.Home.name)
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 5.dp)
        ) {
            Text(stringResource(id = R.string.login_visitor))
        }

        Button(
            onClick =
            {
                updateUser(UserType.Collector.name)
                navigateTo(VinylsScreen.Home.name)
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 5.dp)
        ) {
            Text(stringResource(id = R.string.login_collector))
        }
    }
}

@Composable
@Preview
fun PreviewLogin() {
    Login()
}
