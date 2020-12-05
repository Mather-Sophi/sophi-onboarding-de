from dataprocessor import filereader
import pandas as pd
import unittest

filegw = filereader()

class assesmentTest(unittest.TestCase):
    
    """
    Validate the return value 
    No empty
    return int object
    """
    def test_engagement_time (self):
        eng ='60 minutes'
        pop_inx =12
        read_inx =21
        self.assertIsInstance(filegw._calcengagement_time(eng,pop_inx,read_inx),int)
        self.assertIsNotNone(filegw._calcengagement_time(eng,pop_inx,read_inx))
    
    """"
    Validate the return
    No empty
    return list object
    """
    def test__shapereturnmessage(self):
        df = pd.read_csv(filegw.filename)
        df_test = df[['Author(s) name','Date Submitted']]
        df_test['comp_score'] = 12
        self.assertIsNotNone(filegw._shapereturnmessage(df_test))
        self.assertIsInstance(filegw._shapereturnmessage(df_test),list)


    """
    Validate the return
    No empty
    return string object to be consumed from API calls
    """
    def test_readingcontent(self):
        self.assertIsNotNone(filegw.readingcontent())
        self.assertIsInstance(filegw.readingcontent(),str)



if __name__ == '__name__':
    unittest.main()