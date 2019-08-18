package timely.com.timely.vms

interface ProfileFragmentViewModel {
    var firstNameCallback: (firstName: String) -> Unit
    var middleNameCallback: (middleName: String) -> Unit
    var lastNameCallback: (lastName: String) -> Unit
    var schoolCallback: (school: String) -> Unit
    var strikesCallback: (strikes: String) -> Unit
    var winsCallback: (wins: String) -> Unit

    fun bind()
}