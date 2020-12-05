import pandas as pd
import logging
import json
from errors import processingFileException


class filereader:
    def __init__(self):
        self.filename = 'content/sample-input.csv'
        self._log = logging.getLogger('apilogging')
        
    def readingcontent(self):
        """
        This method process the input data and shape the return message 
        within the API
        """
        try:
            self._log.info("starting the extraction data")
            df = pd.read_csv(self.filename)
            df['comp_score'] = df.apply(lambda x : self._calcengagement_time(x['Total Engagement Time'],x['Popularity index'],x['Readership index']),axis=1)
            lst_records = self._shapereturnmessage(df)
            return str(lst_records)
            
        except Exception as error:
            raise processingFileException(error)
            
            
    def _calcengagement_time(self,engage_tm,pop_inx,read_inx):
        """
        This method calculates the score based on the specific values
        Total Engagement Time , Popularity Index and Readership Index
        finally the method return the contest score per row in the DF
        """
        try:
            self._log.info("calculate the engage time in seconds")
            eng_time_secs = 0
            if 'minutes' in engage_tm:
                time_min = engage_tm.split(' ')[0]
                eng_time_secs = round(int(time_min)*60)
            else:
                time_secs = engage_tm.split(' ')[0]
                eng_time_secs = round(int(time_secs))
            
            self._log.info("calculate total score for the competition")
            comp_score =  eng_time_secs * (pop_inx + read_inx)
            return comp_score
        except Exception as error:
            raise processingFileException(error)
            
    
    def _shapereturnmessage(self,df):
        """
        This method consumes the DF and shape the return output
        throught the API
        """
        try:
            self._log.info("isolate the required columns from DF")
            df_api = df[['Author(s) name','comp_score','Date Submitted']]
            
            def sortlst(record):
                return record['total_score']
            
            
            lst_records = []
            for inx,row in df_api.iterrows():
                autors = row['Author(s) name'].split(";")
                result = [ {"autor" : autor , "total_score": row['comp_score'],"date_submitted":row['Date Submitted']} for autor in autors]
                for record in result:
                    lst_records.append(record)
            lst_records.sort(key=sortlst)
            
            return lst_records
        except Exception as error:
            raise processingFileException(error)
        
