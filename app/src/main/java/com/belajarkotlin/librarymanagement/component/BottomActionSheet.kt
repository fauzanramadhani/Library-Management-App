package com.belajarkotlin.librarymanagement.component


import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.belajarkotlin.librarymanagement.R
import com.belajarkotlin.librarymanagement.data.rememberDropDownBookStateHolder
import com.belajarkotlin.librarymanagement.function.dateToEpoch
import com.belajarkotlin.librarymanagement.function.isNumeric
import com.belajarkotlin.librarymanagement.retrofit.INodeJS
import com.belajarkotlin.librarymanagement.retrofit.RetrofitClient
import com.belajarkotlin.librarymanagement.viewModel.TabsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomActionSheet(
    activityContentScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope, focusManager: FocusManager) -> Unit
) {

    val tabsViewModel: TabsViewModel = viewModel()
    lateinit var myAPI: INodeJS
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val Lexend = FontFamily(
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_bold, FontWeight.Bold)
    )

    val submitState = rememberSaveable() {
        mutableStateOf(true)
    }
    val namaState = remember {
        TextFieldState()
    }
    val nimState = remember {
        TextFieldState()
    }
    val bukuState = rememberDropDownBookStateHolder("")
    val tglPeminjamanState = remember {
        TextFieldState()
    }
    val tglPengembalianState = remember {
        TextFieldState()
    }


    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.White,
        sheetElevation = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetState = state,
        sheetContent = {
            //Action Sheet Title
            Spacer(modifier = Modifier.padding(vertical = 17.dp))
            Surface(
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Tambah Data Peminjaman",
                        fontFamily = Lexend,
                        modifier = Modifier.padding(10.dp),
                    )
                }
            }

            Column {
                CanvasTextField(
                    state = namaState,
                    label = "Nama",
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                    ),
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 15.dp)
                        .fillMaxWidth()
                )

                CanvasTextField(
                    state = nimState,
                    label = "Nim",
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    length = 8,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 15.dp)
                        .fillMaxWidth()
                )

                DropdownBook(
                    stateHolder = bukuState,
                    label = "Buku",
                    focusManager = focusManager,
                )
                DatePicker(state = tglPeminjamanState, label = "Tanggal Peminjaman")
                DatePicker(state = tglPengembalianState, label = "Tenggat Pengembalian")
                Button(
                    text = "Simpan",
                    textColor = Color.White,
                    color = Color(0x2500B98C),
                    enabledState = submitState.value,
                    modifier = Modifier
                        .clip(RoundedCornerShape(2.dp))
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    when {
                        (namaState.text == "") -> Toast.makeText(
                            context,
                            "Silahkan lengkapi kolom nama",
                            Toast.LENGTH_SHORT
                        ).show()
                        (nimState.text == "") -> Toast.makeText(
                            context,
                            "Silahkan lengkapi kolom nim",
                            Toast.LENGTH_SHORT
                        ).show()
                        (!isNumeric(nimState.text)) -> Toast.makeText(
                            context,
                            "Silahkan masukan nim dengan benar",
                            Toast.LENGTH_SHORT
                        ).show()
                        (bukuState.value == "") -> Toast.makeText(
                            context,
                            "Silahkan pilih buku",
                            Toast.LENGTH_SHORT
                        ).show()
                        (tglPeminjamanState.text == "") -> Toast.makeText(
                            context,
                            "Silahkan lengkapi kolom tanggal peminjaman",
                            Toast.LENGTH_SHORT
                        ).show()
                        (tglPengembalianState.text == "") -> Toast.makeText(
                            context,
                            "Silahkan lengkapi kolom tanggal pengembalian",
                            Toast.LENGTH_SHORT
                        ).show()

                        else -> {
                            submitState.value = false
                            val compositeDisposable = CompositeDisposable()
                            val retrofit: Retrofit = RetrofitClient.instance
                            myAPI = retrofit.create(INodeJS::class.java)
                            compositeDisposable.add(myAPI.writeData(
                                nama = namaState.text,
                                nim = nimState.text.toInt(),
                                buku = bukuState.value,
                                tgl_peminjaman = dateToEpoch(date = tglPeminjamanState.text),
                                tgl_pengembalian = dateToEpoch(date = tglPengembalianState.text)
                            )
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnError { e ->
                                    Toast.makeText(context, "Connection Error: Server Offline", Toast.LENGTH_SHORT).show()
                                }
                                .onErrorReturn { e ->
                                    if (e.localizedMessage?.contains("Failed to connect to") == true) {
                                        "Connection Error: Server Offline"
                                    } else {
                                        "Connection Error: ${e.localizedMessage}"
                                    }
                                }
                                .subscribe { message ->
                                    if (message.contains("Successfully Saved")) {
                                        Toast.makeText(context, "Successfully Saved", Toast.LENGTH_SHORT).show()
                                        namaState.text = ""
                                        nimState.text = ""
                                        bukuState.value = ""
                                        tglPeminjamanState.text = ""
                                        tglPengembalianState.text = ""
                                        scope.launch {
                                            state.animateTo(ModalBottomSheetValue.Hidden, tween(100))
                                            tabsViewModel.pullData()
                                        }
                                    }
                                    submitState.value = true
                                }
                            )
                        }
                    }

                }
            }

        }
    ) {
        activityContentScope(state, scope, focusManager)
    }
}