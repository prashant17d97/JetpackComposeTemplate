package com.prashant.composetemplate.screens.login

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.prashant.composetemplate.MainActivity
import com.prashant.composetemplate.R
import com.prashant.composetemplate.navigation.LOGIN


@Composable
fun Login(navHostController: NavHostController, loginVM: LoginVM = hiltViewModel()) {

    val list = arrayListOf(

        "Akaya Kanadaka",
        "Akaya Telivigala",
        "Akronim",
        "Akshar",
        "Aladin",
        "Alata",
        "Alatsi",
        "Albert Sans",
        "Aldrich",
        "Alef",
        "Alegreya",
        "Alegreya SC",
        "Alegreya Sans",
        "Alegreya Sans SC",
        "Aleo",
        "Alex Brush",
        "Alexandria",
        "Alfa Slab One",
        "Alice",
        "Alike",
        "Alike Angular",
        "Alkalami",
        "Allan",
        "Allerta",
        "Allerta Stencil",
        "Allison",
        "Allura",
        "Almarai",
        "Almendra",
        "Almendra Display",
        "Almendra SC",
        "Alumni Sans",
        "Alumni Sans Collegiate One",
        "Alumni Sans Inline One",
        "Alumni Sans Pinstripe",
        "Amarante",
        "Amaranth",
        "Amatic SC",
        "Amethysta",
        "Amiko",
        "Amiri",
        "Amiri Quran",
        "Amita",
        "Anaheim",
        "Andada Pro",
        "Andika",
        "Anek Bangla",
        "Anek Devanagari",
        "Anek Gujarati",
        "Anek Gurmukhi",
        "Anek Kannada",
        "Anek Latin",
        "Anek Malayalam",
        "Anek Odia",
        "Anek Tamil",
        "Anek Telugu",
        "Angkor",
        "Annie Use Your Telescope",
        "Anonymous Pro",
        "Antic",
        "Antic Didone",
        "Antic Slab",
        "Anton",
        "Antonio",
        "Anybody",
        "Arapey",
        "Arbutus",
        "Arbutus Slab",
        "Architects Daughter",
        "Archivo",
        "Archivo Black",
        "Archivo Narrow",
        "Are You Serious",
        "Aref Ruqaa",
        "Aref Ruqaa Ink",
        "Arima",
        "Arima Madurai",
        "Arimo",
        "Arizonia",
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            imageVector = Icons.Default.Lock,
            contentDescription = LOGIN,
            modifier = Modifier.size(80.dp),
            colorFilter = ColorFilter.tint(Color.White.takeIf { isSystemInDarkTheme() }
                ?: Color.Black)
        )

        Text(
            text = "$LOGIN Screen",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            loginVM.login(navHostController)

        }) {
            Text(
                text = LOGIN,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
        MyUI(list)
    }
}

@Preview
@Composable
fun LoginPreview() = Login(rememberNavController())


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyUI(listItems: ArrayList<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(listItems[0]) }
// We want to react on tap/press on TextField to show menu
    Column {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                // The `menuAnchor` modifier must be passed to the text field for correctness.
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                label = { Text("Label") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                listItems.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        },
                        contentPadding = MenuDefaults.DropdownMenuItemContentPadding,
                    )
                }
            }

        }
        var darkTheme by remember {
            mutableStateOf(false)
        }
        ThemeSwitcher(darkTheme = darkTheme) {
            darkTheme = !darkTheme
            (MainActivity.weakReference.get() as MainActivity).darkTheme(boolean = darkTheme)
        }
    }
}

@Composable
fun ThemeSwitcher(
    darkTheme: Boolean = false,
    size: Dp = 150.dp,
    iconSize: Dp = size / 3,
    padding: Dp = 10.dp,
    borderWidth: Dp = 1.dp,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
    onClick: () -> Unit
) {
    val offset by animateDpAsState(
        targetValue = if (darkTheme) 0.dp else size,
        animationSpec = animationSpec
    )

    Box(modifier = Modifier
        .width(size * 2)
        .height(size)
        .clip(shape = parentShape)
        .clickable { onClick() }
        .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = padding)
                .clip(shape = toggleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {}
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    shape = parentShape
                )
        ) {
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(id = R.drawable.nightlight),
                    contentDescription = "Theme Icon",
                    tint = if (darkTheme) MaterialTheme.colorScheme.secondaryContainer
                    else MaterialTheme.colorScheme.primary
                )
            }
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(id = R.drawable.light_mode),
                    contentDescription = "Theme Icon",
                    tint = if (darkTheme) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }
}