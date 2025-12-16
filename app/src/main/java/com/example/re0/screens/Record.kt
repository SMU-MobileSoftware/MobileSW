package com.example.re0.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.re0.model.DailyRecord // ★ [수정] DailyRecord 사용
import com.example.re0.ui.theme.Mint
import com.example.re0.ui.theme.NeonBlue
import com.example.re0.viewModel.AchievementViewModel

@Composable
fun RecordScreen(navController: NavController, backStackEntry: NavBackStackEntry) {
    val viewModel: AchievementViewModel = hiltViewModel()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        RecordContent(innerPadding, viewModel, navController)
    }
}

// 수정/삭제 팝업
@Composable
fun ModifyChallengeDialog(
    initialText: String,
    onDismiss: () -> Unit,
    onUpdate: (String) -> Unit,
    onDelete: () -> Unit
) {
    var text by remember { mutableStateOf(initialText) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("챌린지 관리") },
        text = { TextField(value = text, onValueChange = { text = it }, singleLine = true) },
        confirmButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TextButton(onClick = onDelete) { Text("삭제", color = Color.Red) }
                Button(onClick = { onUpdate(text) }) { Text("수정") }
            }
        },
        dismissButton = { Button(onClick = onDismiss) { Text("취소") } }
    )
}

@Composable
fun RecordContent(
    innerPadding: PaddingValues,
    viewModel: AchievementViewModel,
    navController: NavController
) {
    val uiList by viewModel.uiList.collectAsState()

    // 팝업 상태
    var showModifyDialog by remember { mutableStateOf(false) }
    var selectedRecord by remember { mutableStateOf<DailyRecord?>(null) } // ★ [수정] 타입 변경

    if (showModifyDialog && selectedRecord != null) {
        ModifyChallengeDialog(
            initialText = selectedRecord!!.title,
            onDismiss = { showModifyDialog = false },
            onUpdate = { newTitle ->
                viewModel.updateChallenge(selectedRecord!!, newTitle)
                showModifyDialog = false
            },
            onDelete = {
                viewModel.deleteChallenge(selectedRecord!!)
                showModifyDialog = false
            }
        )
    }

    // Progress 계산
    val totalGoals = uiList.size
    val totalChecked = uiList.count { it.isDone } // ★ [수정] isDone 사용
    val progressPercent = if (totalGoals == 0) 0 else ((totalChecked.toFloat() / totalGoals.toFloat()) * 100).toInt()
    val progressFraction = progressPercent / 100f

    // 요일 변수 (기존과 동일)
    val garbageState by viewModel.garbageState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth().padding(innerPadding).padding(16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(29.dp)
    ) {
        // [Progress Bar]
        Column(
            modifier = Modifier.border(1.dp, Mint, RoundedCornerShape(10.dp)).fillMaxWidth().padding(7.dp)
        ) {
            Text("Progress Bar", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
            Row(modifier = Modifier.fillMaxWidth().height(42.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Box(modifier = Modifier.width(50.dp).height(42.dp).background(NeonBlue, RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
                    Text("$progressPercent%", color = Color.White)
                }
                Box(modifier = Modifier.width(285.dp).height(42.dp).border(2.dp, Color(0xFF79ECF4), RoundedCornerShape(13.dp)).background(Color.White, RoundedCornerShape(13.dp)).padding(4.dp)) {
                    Box(modifier = Modifier.fillMaxHeight().width(285.dp * progressFraction).background(NeonBlue, RoundedCornerShape(10.dp)))
                }
            }
        }

        // [쓰레기 버리는 날]
        Column(
            modifier = Modifier.border(1.dp, Mint, RoundedCornerShape(10.dp))
        ) {
            Column(modifier = Modifier.fillMaxWidth().drawBehind { drawLine(Color(0xFF79ECF4), Offset(0f, size.height), Offset(size.width, size.height), 5f) }.padding(5.dp)) {
                Text("쓰레기 버리는 날", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                GarbageDayItem("월", garbageState.mon) { viewModel.updateGarbageDay("월", it) }
                GarbageDayItem("화", garbageState.tue) { viewModel.updateGarbageDay("화", it) }
                GarbageDayItem("수", garbageState.wed) { viewModel.updateGarbageDay("수", it) }
                GarbageDayItem("목", garbageState.thu) { viewModel.updateGarbageDay("목", it) }
                GarbageDayItem("금", garbageState.fri) { viewModel.updateGarbageDay("금", it) }
                GarbageDayItem("토", garbageState.sat) { viewModel.updateGarbageDay("토", it) }
                GarbageDayItem("일", garbageState.sun) { viewModel.updateGarbageDay("일", it) }
            }
        }

        // [나만의 목표 리스트]
        Column(
            modifier = Modifier.border(1.dp, Mint, RoundedCornerShape(10.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().clickable { navController.navigate("challenge") }.drawBehind { drawLine(Color(0xFF79ECF4), Offset(0f, size.height), Offset(size.width, size.height), 5f) }.padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("나만의 목표", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("목표를 눌러서 수정/삭제 하세요", fontSize = 16.sp, color = Color.Gray)
            }

            Column(
                modifier = Modifier.background(Color(0xEBFFFFFF), RoundedCornerShape(15.dp)).padding(10.dp)
            ) {
                if (uiList.isEmpty()) {
                    Text("오늘의 목표를 등록해주세요.", modifier = Modifier.padding(10.dp), color = Color.Gray)
                } else {
                    uiList.forEach { item ->
                        RecordGoalItem(
                            text = item.title, // ★ [수정] item.title로 직접 접근
                            checked = item.isDone, // ★ [수정] item.isDone으로 직접 접근
                            onCheckedChange = { checked -> viewModel.toggleCheck(item, checked) },
                            onTextClick = {
                                selectedRecord = item
                                showModifyDialog = true
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecordGoalItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTextClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(54.dp).padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange, modifier = Modifier.scale(1.4f))
        Row(
            modifier = Modifier.weight(1f).height(42.dp).background(Color(0xFFE1E7E9), RoundedCornerShape(13.dp)).clickable { onTextClick() }.padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text, fontSize = 16.sp, color = Color.Gray)
        }
    }
}

@Composable
fun GarbageDayItem(day: String, description: String, onDescriptionChange: (String) -> Unit) {
    // (기존 코드와 동일하므로 생략 가능, 혹은 기존 코드를 그대로 두세요)
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        EditDescriptionDialog(initialText = description, onConfirm = { onDescriptionChange(it); showDialog = false }, onCancel = { showDialog = false })
    }
    Column(modifier = Modifier.height(130.dp).width(43.dp).background(Color(0x2601E7C5), RoundedCornerShape(5.dp)).padding(4.dp), verticalArrangement = Arrangement.spacedBy(17.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = day, fontSize = 17.sp, fontWeight = FontWeight.W400)
        Column(modifier = Modifier.width(38.dp).padding(vertical = 14.dp).clickable { showDialog = true }, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = description, fontSize = 11.sp, color = Color.Black)
        }
    }
}

@Composable
fun EditDescriptionDialog(initialText: String, onConfirm: (String) -> Unit, onCancel: () -> Unit) {
    // (기존 코드와 동일)
    var text by remember { mutableStateOf(initialText) }
    AlertDialog(onDismissRequest = onCancel, title = { Text("설명 수정") }, text = { TextField(value = text, onValueChange = { text = it }, singleLine = true) }, confirmButton = { Button(onClick = { onConfirm(text) }) { Text("확인") } }, dismissButton = { Button(onClick = onCancel) { Text("취소") } })
}