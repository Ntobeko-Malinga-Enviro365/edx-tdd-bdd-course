"""
Test Cases TestAccountModel
"""
from unittest import TestCase
from models import db
from models.account import Account, DataValidationError
from factories import AccountFactory


class TestAccountModel(TestCase):
    """Test Account Model"""

    @classmethod
    def setUpClass(cls):
        """ Load data needed by tests """
        db.create_all()  # make our sqlalchemy tables

    @classmethod
    def tearDownClass(cls):
        """Disconnext from database"""
        db.session.close()

    def setUp(self):
        """Truncate the tables"""
        db.session.query(Account).delete()
        db.session.commit()

    def tearDown(self):
        """Remove the session"""
        db.session.remove()

    ######################################################################
    #  T E S T   C A S E S
    ######################################################################

    def test_create_all_accounts(self):
        """ Test creating multiple Accounts """
        for _ in range(10):
            account = AccountFactory()
            account.create()
        self.assertEqual(len(Account.all()), 10)

    def test_create_an_account(self):
        """ Test Account creation using known data """
        account = AccountFactory() # get a random account
        account.create()
        self.assertEqual(len(Account.all()), 1)

    def test_repr(self):
        """Test the representation of an account"""
        account = Account()
        account.name = "Foo"
        self.assertEqual(str(account), "<Account 'Foo'>")

    def test_to_dict(self):
        """ Test account to dict """
        account = AccountFactory() # get a random account
        result = account.to_dict()
        self.assertEqual(account.name, result["name"])
        self.assertEqual(account.email, result["email"])
        self.assertEqual(account.phone_number, result["phone_number"])
        self.assertEqual(account.disabled, result["disabled"])
        self.assertEqual(account.date_joined, result["date_joined"])

    def test_from_dict(self):
        """ Test account from dict """
        account = AccountFactory() # get a random account
        data = account.to_dict()
        account.from_dict(data)
        self.assertEqual(account.name, data["name"])
        self.assertEqual(account.email, data["email"])
        self.assertEqual(account.phone_number, data["phone_number"])
        self.assertEqual(account.disabled, data["disabled"])

    def test_update_an_account(self):
        """ Test Account update using known data """
        account = AccountFactory() # get a random account
        account.create()
        self.assertIsNotNone(account.id)
        account.name = "Rumpelstiltskin"
        account.update()
        found = Account.find(account.id)
        self.assertEqual(found.name, "Rumpelstiltskin")

    def test_invalid_id_on_update(self):
        """ Test invalid ID update """
        account = AccountFactory() # get a random account
        account.id = None
        self.assertRaises(DataValidationError, account.update)

    def test_delete_an_account(self):
        """ Test Account delete using known data """
        account = AccountFactory() # get a random account
        account.create()
        self.assertEqual(len(Account.all()), 1)
        account.delete()
        self.assertEqual(len(Account.all()), 0)

