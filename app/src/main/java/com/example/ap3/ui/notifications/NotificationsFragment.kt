package com.example.ap3.ui.notifications

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ap3.R
import com.example.ap3.login.Home

class NotificationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        val profileMenu = root.findViewById<View>(R.id.menu_profile)
        val aboutMenu= root.findViewById<View>(R.id.menu_about)
        val logoutMenu= root.findViewById<View>(R.id.menu_logout)

        profileMenu.setOnClickListener {
            navigateToProfile()
        }
        aboutMenu.setOnClickListener {
            showAboutAppDialog()
        }
        logoutMenu.setOnClickListener {
            // Implement logout functionality here
            Logout()
        }

        return root
    }

    private fun navigateToProfile() {
        // Navigate to the Profile screen
        val intent = Intent(requireContext(), ProfileActivity::class.java)
        startActivity(intent)
    }
    private fun Logout() {
        // Navigate to the Profile screen
        val intent = Intent(requireContext(), Home::class.java)
        startActivity(intent)
    }
    private fun showAboutAppDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.dialog_about_app, null)

        builder.setView(dialogView)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }
}
