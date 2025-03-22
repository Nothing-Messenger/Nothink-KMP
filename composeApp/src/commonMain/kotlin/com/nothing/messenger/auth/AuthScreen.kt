package com.nothing.messenger.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nothing.auth.api.AuthComponent
import com.nothing.auth.api.data.AuthUiState
import com.nothing.messenger.widget.AppProgressBar
import com.nothing.messenger.widget.AppTextField
import com.nothing.messenger.widget.EmptyScreen
import com.nothing.messenger.widget.ErrorScreen

@Composable
fun AuthScreen(
    component: AuthComponent, modifier: Modifier = Modifier
) {

    val uiState by component.state.collectAsState()

    Scaffold(
        modifier = modifier,
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is AuthUiState.Loading -> AppProgressBar()
                is AuthUiState.Empty -> EmptyScreen()
                is AuthUiState.SignIn -> SignInScreen(component, state)
                is AuthUiState.SignUp -> SignUpScreen(component, state)
                is AuthUiState.ForgotPassword -> ForgotPasswordScreen(component)
                is AuthUiState.Error -> ErrorScreen()
            }
        }
    }
}

@Composable
fun SignInScreen(component: AuthComponent, loginState: AuthUiState.SignIn) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign In Screen",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        AppTextField(
            value = loginState.signInUiModel.login,
            onValueChange = { component.onLoginChanged(it) },
            label = "Login",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 0.dp),
        )

        AppTextField(
            value = loginState.signInUiModel.password,
            onValueChange = { component.onPasswordChanged(it) },
            label = "Password",
            isPassword = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )

        Button(
            onClick = { component.onLoginClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 8.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text("Login", color = Color.White)
        }

        Button(
            onClick = { component.showSignUp() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 8.dp),
        ) {
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.body2,
            )
        }

        TextButton(
            onClick = { component.onForgotPasswordClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
        ) {
            Text(
                text = "Forgot Password?",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
fun SignUpScreen(component: AuthComponent, loginState: AuthUiState.SignUp) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign Up Screen",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        AppTextField(
            value = loginState.signUpUiModel.login,
            onValueChange = { component.onLoginChanged(it) },
            label = "Login",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 0.dp),
        )

        AppTextField(
            value = loginState.signUpUiModel.password,
            onValueChange = { component.onPasswordChanged(it) },
            label = "Password",
            isPassword = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 0.dp),
        )

        AppTextField(
            value = loginState.signUpUiModel.passwordRepeat,
            onValueChange = { component.onPasswordChanged(it) },
            label = "Password",
            isPassword = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )

        Button(
            onClick = { component.onRegisterClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 8.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text("Register", color = Color.White)
        }

        Button(
            onClick = { component.showSignIn() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 8.dp),
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.body2,
            )
        }

        TextButton(
            onClick = { component.onForgotPasswordClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
        ) {
            Text(
                text = "Forgot Password?",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
fun ForgotPasswordScreen(component: AuthComponent) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Forgot Password Screen")
        Button(onClick = { component.showSignIn() }) {
            Text("Back to Login")
        }
    }
}