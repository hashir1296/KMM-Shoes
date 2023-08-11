import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ZStack {
//            Color.black.ignoresSafeArea()
            Color(red: 32.0/255.0, green: 32.0/255.0, blue: 43.0/255.0).ignoresSafeArea()
//            Color.green.ignoresSafeArea()
            ComposeView()
        }.preferredColorScheme(.dark)
    }
}



