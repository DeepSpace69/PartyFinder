import { TestBed } from '@angular/core/testing';

import { FindPartiesComponent } from './findParties.component';

describe('FindParties Component', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({declarations: [FindPartiesComponent]});
  });

  it('should ...', () => {
    const fixture = TestBed.createComponent(FindPartiesComponent);
    fixture.detectChanges();
    expect(fixture.nativeElement.children[0].textContent).toContain('About Works!');
  });

});
