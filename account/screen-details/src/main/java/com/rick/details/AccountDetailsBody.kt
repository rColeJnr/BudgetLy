package com.rick.details

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rick.budgetly.components.IconDropdownMenu
import com.rick.budgetly.feature_account.domain.AccountIcon
import com.rick.budgetly.feature_account.ui.util.TestTags
import com.rick.budgetly.feature_account.ui.util.formatAmount
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AccountDetailsBody(
    modifier: Modifier = Modifier,
    onNavigationIconClick: () -> Unit,
    onSettingsClick: (Int) -> Unit,
    navController: NavHostController,
    viewModel: AccountDetailsViewModel = hiltViewModel(),
) {

    val scope = rememberCoroutineScope()
    val scaffoldSate = rememberScaffoldState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldSate,
        topBar = {
            TopAppBar(
                modifier = modifier,
                backgroundColor = Color.White,
                title = {},
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "NavigateUp",
                        modifier = Modifier.clickable { onNavigationIconClick() }
                    )
                },
                actions = {
                    IconButton(onClick = { onSettingsClick( viewModel.accountId!!) } ){ Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    ) }
                    Spacer(modifier = modifier.width(8.dp))
                    DetailsDropDownMenu(
                        icon = Icons.Default.MoreVert,
                        contentDescription = TestTags.detailsMoreVert,
                        onMenuItemOneClick = { viewModel.onEvent( AccountDetailsEvents.ChangeMainStatus) },
                        menuItemOneContent = { Text(text = "Set as main account") },
                        onMenuItemSecondClick = { viewModel.onEvent( AccountDetailsEvents.ChangeIncludeInTotalStatus) },
                        menuItemSecondContent = { Text(text = if (viewModel.accountInclude.value) "Don't include in total" else "Include in total" ) },
                        onMenuItemThirdClick = {
                            viewModel.onEvent(
                                AccountDetailsEvents.DeleteAccount(viewModel.currentAccount!!)
                            )
                            // We then share data between viewModels, i dont' like this no bit.
                            CoroutineScope(Dispatchers.Main).launch{
                                val job = scope.launch {
                                    simpleSnackbar(scaffoldSate, "Account deleted", "Undo"){
                                        viewModel.onEvent(AccountDetailsEvents.RestoreAccount)
                                    }
                                    delay(250)
                                }
                                job.join()
                                navController.navigateUp()
                            }
                        },
                        menuItemThirdContent = { Text(text = "Delete account") }
                    )
                }
            )
        }
    ) {
        Column(
            modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            //theme the god damn app
            val balance = viewModel.accountBalance.value.toFloat()
            Spacer(modifier = modifier.height(32.dp))
            Icon(imageVector = AccountIcon.values()[viewModel.accountIcon.value].imageVector, contentDescription = AccountIcon.values()[viewModel.accountIcon.value].contentDescription, modifier.size(75.dp))
            Spacer(modifier = modifier.height(16.dp))
            Text(text = viewModel.accountTitle.value)
            Spacer(modifier = modifier.height(16.dp))
            Text(text = formatAmount(balance), style = MaterialTheme.typography.h5)
            Spacer(modifier = modifier.height(8.dp))
            Text(viewModel.accountType, style = MaterialTheme.typography.body2)
            Spacer(modifier = modifier.height(24.dp))
            Text(text = "Totals for month")
            Spacer(modifier = modifier.height(16.dp))
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                AccountIncomeExpenses(modifier = Modifier, text = "Income", money = "$ Todo", imageVector = Icons.Default.ArrowCircleDown)
                Spacer(modifier = Modifier.width(8.dp))
                AccountIncomeExpenses(modifier = Modifier, text = "Outcome", money = "$ Todo", imageVector = Icons.Default.ArrowCircleUp)
            }
        }

    }
}

suspend fun simpleSnackbar(
    scaffoldSate: ScaffoldState,
    message: String,
    actionLabel: String,
    onDismiss: () -> Unit
) {
    val result = scaffoldSate.snackbarHostState.showSnackbar(
        message = message,
        actionLabel = actionLabel,
        duration = SnackbarDuration.Long
    )
    if (result == SnackbarResult.ActionPerformed) {
        onDismiss()
    }
}

@Composable
fun AccountIncomeExpenses(modifier: Modifier, text: String, money: String, imageVector: ImageVector) {
    Box(
        modifier = modifier
            .border(width = 2.dp, color = LightGray, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        ConstraintLayout(modifier = modifier
            .wrapContentHeight()
            .width(120.dp)
        ) {
            val (title, amount, icon) = createRefs()

            Text(text = text,
                modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                    }
                    .padding(start = 8.dp, top = 8.dp)
            )
            Text(text = money, modifier = modifier
                .constrainAs(amount) {
                    top.linkTo(title.bottom)
                }
                .padding(start = 8.dp, top = 8.dp)
            )
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = modifier
                    .constrainAs(icon) { top.linkTo(title.top); start.linkTo(title.end) }
                    .padding(top = 8.dp, start = 8.dp),
            )
        }
    }
}

@Composable
fun DetailsDropDownMenu(
    icon: ImageVector,
    contentDescription: String = "",
    onMenuItemOneClick: () -> Unit,
    menuItemOneContent: @Composable () -> Unit,
    onMenuItemSecondClick: () -> Unit,
    menuItemSecondContent: @Composable () -> Unit,
    onMenuItemThirdClick: () -> Unit,
    menuItemThirdContent: @Composable () -> Unit,
) {
    IconDropdownMenu(
        icon = icon,
        cDescription = contentDescription,
        onMenuItemOneClick = { onMenuItemOneClick() },
        menuItemOneContent = { menuItemOneContent() },
        onMenuItemSecondClick = { onMenuItemSecondClick() },
        menuItemSecondContent = { menuItemSecondContent() },
        onMenuItemThirdClick = { onMenuItemThirdClick() },
        menuItemThirdContent = { menuItemThirdContent() }
    )
}