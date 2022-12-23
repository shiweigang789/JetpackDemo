package com.swg.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.ConstraintLayout
import com.swg.compose.ui.theme.JetpackDemoTheme
import com.swg.compose.ui.theme.White

class LoginActivity : ComponentActivity() {

    companion object {
        fun start(context: Context?) {
            context?.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            loginView()
        }
    }

}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun loginView() {
    val usernameRemember = rememberSaveable() {
        mutableStateOf("")
    }
    val passwordRemember = rememberSaveable {
        mutableStateOf("")
    }
    JetpackDemoTheme {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(start = 16.dp, end = 16.dp)
        ) {
            val (back, welcome, wan, username, password, login, sign_in, sign_up) = createRefs()
            val loginTopBarrier = createTopBarrier(login)
            val loginBottomBarrier = createBottomBarrier(login)
            Image(painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 40.dp, height = 40.dp)
                    .padding(10.dp)
                    .constrainAs(back) {
                        top.linkTo(parent.top)
                    }
                    .clickable {

                    },
                contentScale = ContentScale.Inside
            )
            Text(text = "Welcome",
                style = MaterialTheme.typography.h4,
                color = colorResource(id = R.color.white),
                modifier = Modifier.constrainAs(welcome) {
                    top.linkTo(back.bottom, margin = 40.dp)
                })
            Text(text = "玩android",
                style = MaterialTheme.typography.h5,
                color = White,
                modifier = Modifier.constrainAs(wan) {
                    top.linkTo(welcome.bottom, margin = 10.dp)
                })
            TextField(value = usernameRemember.value, placeholder = {
                Text(text = "请输入名称")
            }, maxLines = 1, colors = TextFieldDefaults.textFieldColors(
                textColor = White,
                focusedIndicatorColor = White,
                placeholderColor = White,
                cursorColor = White
            ), onValueChange = {
                usernameRemember.value = it
            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .constrainAs(username) {
                    top.linkTo(wan.bottom, margin = 80.dp)
                })
            TextField(
                value = passwordRemember.value,
                placeholder = {
                    Text(text = "请输入密码")
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = White,
                    placeholderColor = White,
                    textColor = White,
                    focusedIndicatorColor = White
                ),
                onValueChange = {
                    passwordRemember.value = it
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(password) {
                        top.linkTo(username.bottom, margin = 5.dp)
                    }
            )
            Text(
                text = "登录",
                color = White,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.constrainAs(sign_in) {
                    top.linkTo(loginTopBarrier)
                    bottom.linkTo(loginBottomBarrier)
                }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.theme_orange))
                    .constrainAs(login) {
                        top.linkTo(password.bottom, margin = Dp(20f))
                        end.linkTo(parent.end)
                    }
                    .clickable {

                    }
            )
            Text(
                text = "注册",
                color = White,
                fontSize = TextUnit(25F, TextUnitType.Sp),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .constrainAs(sign_up) {
                        bottom.linkTo(parent.bottom, margin = 40.dp)
                    }
                    .clickable {

                    }
            )

        }
    }
}