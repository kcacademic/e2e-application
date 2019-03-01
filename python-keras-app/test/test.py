import unittest

from src import myapp

class TestPredict(unittest.TestCase):
    def test_predict(self):
        self.assertTrue(True)

    def setUp(self):
        myapp.app.testing = True
        self.app = myapp.app.test_client()

    def test_home(self):
        result = self.app.get('/')
        print(result.data)
        assert b'It works!' in result.data

if __name__ == '__main__':
    unittest.main()