package com.belajarkotlin.librarymanagement.component


import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belajarkotlin.librarymanagement.R
import com.belajarkotlin.librarymanagement.doubleClickHandler.clickableSingle
import com.belajarkotlin.librarymanagement.function.epochToDate

@Composable
fun TabsCardView(
    tabs: Int,
    id: Int,
    nama: String,
    nim: Int,
    buku: String,
    tglPeminjaman: Int,
    tglPengembalian: Int,
    dikembalikan: Int,
    status: Int,
    onClickPressed: () -> Unit = {}
) {
    val Lexend = FontFamily(
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_bold, FontWeight.Bold)
    )

    if (tabs == 1 && status == 0) {
        Card(
            backgroundColor = Color(0xFFFFFFFF),
            modifier = Modifier
                .fillMaxWidth()
                .clickableSingle { onClickPressed() }
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
            ) {

                //ID
                Row(
                    verticalAlignment = Alignment.Bottom,

                    ) {
                    Text(
                        text = "ID: ",
                        fontSize = 15.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = id.toString(),
                        fontSize = 15.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                //Nama
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Nama: ",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = nama,
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }

                //NIM
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "NIM: ",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = nim.toString(),
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }

                //Buku
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Buku: ",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = buku,
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }

                //Tanggal Peminjaman
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Tanggal Peminjaman: ",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = epochToDate("HH:mm - dd/MM/yyyy ", tglPeminjaman.toLong()),
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }

                //Tenggat Pengembalian
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Tenggat Pengembalian: ",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = epochToDate("HH:mm - dd/MM/yyyy ", tglPeminjaman.toLong()),
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 1.dp))
    } else if (tabs == 2 && status >= 1) {
        Card(
            backgroundColor = Color(0xFFFFFFFF),
            modifier = Modifier
                .fillMaxWidth()
                .clickableSingle { onClickPressed() }
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
            ) {

                //ID
                Row(
                    verticalAlignment = Alignment.Bottom,

                    ) {
                    Text(
                        text = "ID: ",
                        fontSize = 15.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = id.toString(),
                        fontSize = 15.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                //Nama
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Nama: ",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = nama,
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }

                //NIM
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "NIM: ",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = nim.toString(),
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }

                //Buku
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Buku: ",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = buku,
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }

                //Tanggal Peminjaman
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Tanggal Peminjaman: ",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "${epochToDate("dd/MM/yyyy", tglPeminjaman.toLong())} - ${
                            epochToDate(
                                "HH:mm", tglPeminjaman.toLong()
                            )
                        }",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }
                //Tanggal Dikembalikan
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Tanggal Dikembalikan: ",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "${epochToDate("dd/MM/yyyy", tglPengembalian.toLong())} - ${
                            epochToDate(
                                "HH:mm", dikembalikan.toLong()
                            )
                        }",
                        fontSize = 13.sp,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 1.dp))
    }

}